package com.example.coursematerial.animation.ux

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentAnimationStartBinding
import com.example.coursematerial.databinding.FragmentElevationBinding
import com.example.coursematerial.databinding.FragmentUxTextBinding


class UxTextFragment: Fragment() {

    private var _binding: FragmentUxTextBinding? = null
    private val binding: FragmentUxTextBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUxTextBinding.inflate(inflater, container, false)
        return binding.root


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UxTextFragment()
    }



}