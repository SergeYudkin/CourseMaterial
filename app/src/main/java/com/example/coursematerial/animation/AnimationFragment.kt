package com.example.coursematerial.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.example.coursematerial.databinding.FragmentAnimationBinding


class AnimationFragment: Fragment() {

    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root


    }
        private var isFlagAnimation = false
        var duration = 2000L

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titles: MutableList<String> = ArrayList()
        for (i in 0..4) {
            titles.add("Item $i")
        }


        binding.fab.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            if (isFlagAnimation){
                ObjectAnimator.ofFloat(binding.plusImageview,View.ROTATION, 0f,675f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer,View.TRANSLATION_Y, -140f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer,View.TRANSLATION_Y, -250f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.transparentBackground,View.ALPHA,0.5f).setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(1f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator){
                            binding.optionOneContainer.isClickable = true

                        }
                    }
                )

                binding.optionTwoContainer.animate().alpha(1f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator){
                            binding.optionTwoContainer.isClickable = true

                        }
                    }
                )

            }else{
                ObjectAnimator.ofFloat(binding.plusImageview,View.ROTATION, 675f,0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer,View.TRANSLATION_Y, 0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer,View.TRANSLATION_Y, 0f).setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.transparentBackground,View.ALPHA,0f).setDuration(duration).start()

                binding.optionOneContainer.animate().alpha(0f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator){
                            binding.optionOneContainer.isClickable = false

                        }
                    }
                )

                binding.optionTwoContainer.animate().alpha(0f).setDuration(duration).setListener(
                    object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator){
                            binding.optionTwoContainer.isClickable = false

                        }
                    }
                )
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