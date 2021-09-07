package xyz.beathub.beathub_android.fragments

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xyz.beathub.beathub_android.USER_AUTH_KEY
import xyz.beathub.beathub_android.USER_ID_KEY
import xyz.beathub.beathub_android.adapters.RepoAdapter
import xyz.beathub.beathub_android.backgroundThreadShortToast
import xyz.beathub.beathub_android.databinding.FragmentOptionsBinding
import xyz.beathub.beathub_android.databinding.FragmentRepoBinding
import xyz.beathub.beathub_android.models.networking.AccountResponse
import xyz.beathub.beathub_android.modules.ApiModule

class OptionsFragment : Fragment() {
    private var _binding: FragmentOptionsBinding? = null

    private val binding get() = _binding!!

    private val deleteResultLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentOptionsBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deleteResultLiveData.observe(this.viewLifecycleOwner){
            if (it){
                OptionsFragmentDirections.actionLogout()
                    .apply {
                    }
                    .let { findNavController().navigate(it) }
            }
        }
        initToolbar()
        initDeleteButton()

    }

    private fun initToolbar() {
        binding.logoutImage.setOnClickListener {
            RepoFragmentDirections.actionLogout().let {
                findNavController().navigate(it)
            }
        }
        binding.optionsImage.setOnClickListener {
            RepoFragmentDirections.actionOptions().let {
                findNavController().navigate(it)
            }
        }
    }

    private fun initDeleteButton() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.apply {
                setMessage("Are you sure you want to delete your account?")
                //setTitle("Account deletion")
//                setItems(
//                    arrayOf(
//                        "Yes, I want to delete my account."
//                    )
//                ) { _, which ->
//                    when (which) {
//                        0 -> deleteAccount()
//                    }
//                }
                setPositiveButton(
                    "Delete"
                ) { _, _ -> deleteAccount()
                }
                setNegativeButton(
                    "Cancel"
                ) { _, _ ->
                }
            }
            builder.create()
        }
        binding.btnDelete.setOnClickListener {
            alertDialog?.show()
        }
    }
    private fun deleteAccount(){
        ApiModule.retrofit.delete(requireActivity().getPreferences(Context.MODE_PRIVATE).getString(
            USER_ID_KEY, "-1")
            .orEmpty()).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                backgroundThreadShortToast(requireActivity(),"Account deleted successfully!")
                deleteResultLiveData.postValue(true)

            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                backgroundThreadShortToast(requireActivity(),"Error!")
            }
        })
    }
}