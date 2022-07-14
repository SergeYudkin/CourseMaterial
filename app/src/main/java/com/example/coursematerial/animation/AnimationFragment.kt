package com.example.coursematerial.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.databinding.FragmentAnimationBinding
import com.example.coursematerial.databinding.FragmentSecondBinding
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.EpicViewModel

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


        binding.animateButtonOne.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val myAutoTransition = TransitionSet()
            myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER

            val fade = Fade()
            val changeBounds = ChangeBounds()
            myAutoTransition.addTransition(fade)
            myAutoTransition.addTransition(changeBounds)

            TransitionManager.beginDelayedTransition(binding.transitionContainer,myAutoTransition)
            binding.animateText.visibility = if (isFlagAnimation) View.VISIBLE else{
               View.GONE
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