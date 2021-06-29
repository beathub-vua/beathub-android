package xyz.beathub.beathub_android

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.register_activity.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody.Companion.create
import okio.IOException
import org.json.JSONObject


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        init()
    }

    private fun init() {
        btnCancel.setOnClickListener {
            finish()
        }
        btnRegisterSend.setOnClickListener {
            if (etUsername.length()<=2){
                Toast.makeText(this, "Username too short.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etPassword.text.toString()!=etConfirmPassword.text.toString() || etPassword.text.toString().length<=3){
                Toast.makeText(this, "Passwords are not the same or too short.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etEmail.text.toString().isValidEmail()){
                Toast.makeText(this, "E-mail is invalid.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var url = URL + "/accounts/register"
            val jsonObject = JSONObject()

            jsonObject.put("username", etUsername.text.toString())
            jsonObject.put("password", etPassword.text.toString())
            jsonObject.put("email", etEmail.text.toString())

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
                        backgroundThreadShortToast(getApplicationContext(),"Registration successful!")
                        finish()
                    }else{
                        backgroundThreadShortToast(getApplicationContext(),resp)
                    }

                }
                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())

                    backgroundThreadShortToast(getApplicationContext(),e.message.toString())
                }
            })


        }

    }
}