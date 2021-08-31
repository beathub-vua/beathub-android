package xyz.beathub.beathub_android.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash.*
import xyz.beathub.beathub_android.R
import xyz.beathub.beathub_android.applyAnimation
import xyz.beathub.beathub_android.databinding.FragmentSplashBinding




class SplashFragment : Fragment() {
    private val DELAY: Long = 3000

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startAnimations()
        redirect()
    }


    private fun startAnimations() {
        ivSplash.applyAnimation(R.anim.rotate)
        tvSplash.applyAnimation(R.anim.enlarge)
    }

    private fun redirect() {

        Handler(Looper.getMainLooper()).postDelayed(
                { SplashFragmentDirections.actionSplashToLogin()
                    .let { findNavController().navigate(it) } },
                DELAY
        )
    }
}