package com.example.coursematerial.animation.ux

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.databinding.FragmentUxTutorialBinding
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType


class UxTutorialFragment: Fragment() {

    private var _binding: FragmentUxTutorialBinding? = null
    private val binding: FragmentUxTutorialBinding
        get() = _binding!!


     companion object {
        fun newInstance() = UxTutorialFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUxTutorialBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            if (isAdded)// проверяем, не умер ли фрагент
                show()
        }, 500)

    }

    private fun show() {
        GuideView.Builder(requireContext())
            .setTitle("Guide Title Text")
            .setContentText("Guide Description Text\n .....Guide Description Text\n .....Guide Description Text .....")
            .setTargetView(binding.btnBad)
            .setDismissType(DismissType.anywhere)
            .build()
            .show()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}