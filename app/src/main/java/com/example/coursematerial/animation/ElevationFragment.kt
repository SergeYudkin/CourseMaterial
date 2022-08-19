package com.example.coursematerial.animation

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
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentAnimationStartBinding
import com.example.coursematerial.databinding.FragmentElevationBinding


class ElevationFragment: ViewBindingFragment<FragmentElevationBinding>(FragmentElevationBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ElevationFragment()
    }

}