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


        binding.scrollView.setOnScrollChangeListener { _, _, _, _, _ ->
            binding.header.isSelected = binding.scrollView.canScrollVertically(-1)
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