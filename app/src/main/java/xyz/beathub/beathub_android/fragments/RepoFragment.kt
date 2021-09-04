package xyz.beathub.beathub_android.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import xyz.beathub.beathub_android.databinding.FragmentRepoBinding

class RepoFragment : Fragment() {

    private var _binding: FragmentRepoBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRepoBinding.inflate(inflater, container, false)
        initToolbar()

        return binding.root
    }

    private fun initToolbar() {
        binding.logoutImage.setOnClickListener {
            RepoFragmentDirections.actionLogout().let {
                findNavController().navigate(it)
            }
        }
//        binding.logoutImage.setOnClickListener {
//            RepoFragmentDirections.actionLogout().let {
//                findNavController().navigate(it)
//            }
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}