package com.example.coursematerial.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentAnimationBinding
import com.example.coursematerial.databinding.FragmentAnimationStartBinding


class AnimationFragment: Fragment() {

    private var _binding: FragmentAnimationStartBinding? = null
    private val binding: FragmentAnimationStartBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimationStartBinding.inflate(inflater, container, false)
        return binding.root


    }
        private var isFlagAnimation = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val constraintSetStart = ConstraintSet()
        val constraintSetEnd = ConstraintSet()
        //constraintSetStart.clone(binding.constraintContainer)
        constraintSetStart.clone(context, R.layout.fragment_animation_start)
        constraintSetEnd.clone(context, R.layout.fragment_animation_end)



        binding.tap.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val changeBounds = ChangeBounds()
            changeBounds.duration = 1100L
            changeBounds.interpolator =  AnticipateOvershootInterpolator(5.0f)
            TransitionManager.beginDelayedTransition(binding.constraintContainer,changeBounds)


            if (isFlagAnimation){

                constraintSetEnd.applyTo(binding.constraintContainer)

            }else{
                constraintSetStart.applyTo(binding.constraintContainer)
            }
        }

    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AnimationFragment()
    }



}