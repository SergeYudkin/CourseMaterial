package com.example.coursematerial.animation

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.databinding.FragmentPausePlayBinding
import com.example.coursematerial.databinding.FragmentSplashBinding
import com.example.coursematerial.view.MainActivity


class SplashFragment: Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding: FragmentSplashBinding
        get() {
            return _binding!!
        }

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

        binding.imageViewMount.animate().rotation(720f).setDuration(2000L).start()

       //  ObjectAnimator.ofFloat(binding.imageViewMount,View.ROTATION, 720f).setDuration(2000L).start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(requireActivity(),MainActivity::class.java))

        },2000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SplashFragment()
    }



}