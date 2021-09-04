package xyz.beathub.beathub_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONObject
import xyz.beathub.beathub_android.R
import xyz.beathub.beathub_android.URL
import xyz.beathub.beathub_android.backgroundThreadShortToast
import xyz.beathub.beathub_android.databinding.FragmentRegisterBinding
import xyz.beathub.beathub_android.isValidEmail


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registrationResultLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {

        registrationResultLiveData.observe(this.viewLifecycleOwner) {
            if (it) {
                RegisterFragmentDirections.actionRegisterToLogin()
                    .apply {
                        this.email = binding.etEmail.text.toString()
                    }
                    .let { findNavController().navigate(it) }
            }
        }

        binding.btnCancel.setOnClickListener {
            RegisterFragmentDirections.actionRegisterToLogin()
                .apply {
                    this.email = binding.etEmail.text.toString()
                }
                .let { findNavController().navigate(it) }
        }
        binding.btnRegisterSend.setOnClickListener {
            if (binding.etUsername.length() <= 2) {
                Toast.makeText(requireContext(), "Username too short.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (binding.etPassword.text.toString() != binding.etConfirmPassword.text.toString() || binding.etPassword.text.toString().length <= 2) {
                Toast.makeText(
                    requireContext(),
                    "Passwords are not the same or too short.",
                    Toast.LENGTH_SHORT
                )
                    .show()
                return@setOnClickListener
            }
            if (binding.etEmail.text.toString().isValidEmail()) {
                Toast.makeText(requireContext(), "E-mail is invalid.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            var url = URL + "/accounts/register"
            val jsonObject = JSONObject()

            jsonObject.put("username", binding.etUsername.text.toString())
            jsonObject.put("password", binding.etPassword.text.toString())
            jsonObject.put("email", binding.etEmail.text.toString())

            val client = OkHttpClient()
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val body = jsonObject.toString().toRequestBody(mediaType)
            val request: Request = Request.Builder()
                .url(url)
                .post(body)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val resp = response.body?.string()
                    println(resp)
                    if (response.code == 200) {
                        registrationResultLiveData.postValue(true)
                        backgroundThreadShortToast(
                            requireContext(),
                            resp
                        )
                    } else {
                        backgroundThreadShortToast(requireContext(), resp)
                    }

                }

                override fun onFailure(call: Call, e: IOException) {
                    println(e.message.toString())

                    backgroundThreadShortToast(requireContext(), e.message.toString())
                }
            })


        }

    }
}