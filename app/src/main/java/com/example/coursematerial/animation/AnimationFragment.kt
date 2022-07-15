package com.example.coursematerial.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.example.coursematerial.R
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
        var isFlagAnimation = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAnimation.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val params = it.layoutParams as FrameLayout.LayoutParams

            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeBounds.setPathMotion(ArcMotion())

            TransitionManager.beginDelayedTransition(binding.root,changeBounds)
            if (isFlagAnimation){
                params.gravity = Gravity.TOP or Gravity.START

            }else{
                params.gravity = Gravity.BOTTOM or Gravity.END
            }
            it.layoutParams = params







           /* val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeImageTransform.duration = 2000L


            transitionSet.addTransition(changeBounds)   // важен порядок выполнения
                transitionSet.addTransition(changeImageTransform)
                TransitionManager.beginDelayedTransition(binding.root,transitionSet)*/

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