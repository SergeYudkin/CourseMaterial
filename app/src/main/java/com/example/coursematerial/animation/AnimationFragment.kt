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

        val constraintSet = ConstraintSet()

        //constraintSetStart.clone(binding.constraintContainer)
        constraintSet.clone(context, R.layout.fragment_animation_start)




        binding.tap.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val changeBounds = ChangeBounds()
            changeBounds.duration = 1100L
            changeBounds.interpolator =  AnticipateOvershootInterpolator(5.0f)
            TransitionManager.beginDelayedTransition(binding.constraintContainer,changeBounds)


            if (isFlagAnimation){

                constraintSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.END)
                //constraintSet.clear(R.id.title,ConstraintSet.END)

            }else{
                constraintSet.connect(R.id.title,ConstraintSet.END,R.id.backgroundImage,ConstraintSet.START)
            }
            constraintSet.applyTo(binding.constraintContainer)
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