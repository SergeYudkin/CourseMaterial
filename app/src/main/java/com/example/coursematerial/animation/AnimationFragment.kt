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


class AnimationFragment: ViewBindingFragment<FragmentAnimationStartBinding>(FragmentAnimationStartBinding::inflate) {


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

    companion object {
        @JvmStatic
        fun newInstance() =
            AnimationFragment()
    }

}