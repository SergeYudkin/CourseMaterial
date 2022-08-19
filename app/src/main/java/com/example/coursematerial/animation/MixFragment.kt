package com.example.coursematerial.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentAnimationStartBinding
import com.example.coursematerial.databinding.FragmentMixBinding
import java.util.ArrayList


class MixFragment: ViewBindingFragment<FragmentMixBinding>(FragmentMixBinding::inflate) {

        private var isFlagAnimation = false
            var duration = 1000L


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titles: MutableList<String> = ArrayList()
        for (i in 0..20){
            titles.add("item $i")
        }


        binding.button.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            TransitionManager.beginDelayedTransition(binding.root)
            binding.transitionsContainer.removeAllViews()

            titles.shuffle()
            titles.forEach {
                binding.transitionsContainer.addView(TextView(context).apply {
                    text = it
                    ViewCompat.setTransitionName(this,it)
                })
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MixFragment()
    }



}