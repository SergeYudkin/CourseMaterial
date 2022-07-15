package com.example.coursematerial.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        var isFlagAnimation = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewAnimation.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val params = it.layoutParams as LinearLayout.LayoutParams


            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeImageTransform.duration = 2000L


            transitionSet.addTransition(changeBounds)   // важен порядок выполнения
                transitionSet.addTransition(changeImageTransform)
                TransitionManager.beginDelayedTransition(binding.root,transitionSet)
            if (isFlagAnimation){
                params.height = LinearLayout.LayoutParams.MATCH_PARENT
                (it as ImageView).scaleType = ImageView.ScaleType.CENTER_CROP      // можно так записать
            }else{
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                binding.imageViewAnimation.scaleType = ImageView.ScaleType.CENTER_INSIDE  // или так
            }
            it.layoutParams = params
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