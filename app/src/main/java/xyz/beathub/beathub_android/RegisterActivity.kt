package xyz.beathub.beathub_android

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.register_activity.*
import okhttp3.*
import okio.IOException


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
            if (etUsername.length()<5){
                Toast.makeText(this, "Username too short.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etPassword.text.toString()!=etConfirmPassword.text.toString()){
                Toast.makeText(this, "Passwords are not the same.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etEmail.text.toString().isValidEmail()){
                Toast.makeText(this, "E-mail is invalid.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var url = URL + "/accounts/register"

            val formBody = FormBody.Builder()
                .add("username", etUsername.text.toString())
                .add("password", etPassword.text.toString())
                .add("email", etEmail.text.toString())
                .build()

            var request = Request.Builder().url(url)
                .post(formBody)
                .build()

            var client = OkHttpClient();
            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response){
                    println(response.body?.string())
                    if (response.body?.string()==""){
                        Toast.makeText(baseContext, "Registration successful!", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(baseContext, response.body?.string(), Toast.LENGTH_LONG).show()
                    }


                }

                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())

                    Toast.makeText(baseContext, e.message.toString(), Toast.LENGTH_LONG).show()
                }
            })


        }

    }
}