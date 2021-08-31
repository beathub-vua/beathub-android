package xyz.beathub.beathub_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject
import xyz.beathub.beathub_android.R
import xyz.beathub.beathub_android.URL
import xyz.beathub.beathub_android.backgroundThreadShortToast
import xyz.beathub.beathub_android.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        binding.btnLogin.setOnClickListener {
            if (binding.etLoginUsername.length() <= 0) {
                Toast.makeText(this, "Please enter username.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.etLoginPassword.text.toString().length <= 0) {
                Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            var url = URL + "/login"
            val jsonObject = JSONObject()

            jsonObject.put("username", binding.etLoginUsername.text.toString())
            jsonObject.put("password", binding.etLoginPassword.text.toString())


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
                        backgroundThreadShortToast(requireContext(),response.header("authorization"))
                        //finish()
                    }else{
                        backgroundThreadShortToast(requireContext(),"Incorrect username and password combination.")
                        println(resp)

                    }

                }
                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())

                    backgroundThreadShortToast(requireContext(),e.message.toString())
                }
            })
        }

        binding.btnRegister.setOnClickListener {
            LoginFragmentDirections.actionLoginToRegister()
                .let { findNavController().navigate(it) }
        }
    }
}