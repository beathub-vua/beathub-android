package xyz.beathub.beathub_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.register_activity.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.login_activity)
//setContentView(R.layout.activity_main)
//        initHamburgerMenu()
      init()
    }

    private fun init() {
        btnLogin.setOnClickListener {
            if (etLoginUsername.length() <= 0) {
                Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etLoginPassword.text.toString().length <= 0) {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            var url = URL + "/login"
            val jsonObject = JSONObject()

            jsonObject.put("username", etLoginUsername.text.toString())
            jsonObject.put("password", etLoginPassword.text.toString())


            val client = OkHttpClient()
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)
            val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response){
                    val resp = response.body?.string()
                    println(resp)
                    if (resp==""){
//                        backgroundThreadShortToast(getApplicationContext(),"Login successful!")
                        backgroundThreadShortToast(applicationContext,response.header("authorization"))
                        //finish()
                    }else{
                        backgroundThreadShortToast(applicationContext,"Incorrect username and password combination.")
                        println(resp)

                    }

                }
                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())

                    backgroundThreadShortToast(applicationContext,e.message.toString())
                }
            })
        }

        btnRegister.setOnClickListener {
            startActivity<RegisterActivity>()
        }
    }
}