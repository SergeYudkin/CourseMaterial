package com.example.coursematerial.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentEarthBinding
import com.example.coursematerial.databinding.FragmentSettingsBinding

class EarthFragment: Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() {
            return _binding!!
        }

    private var isFlagAnimation = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewEarth.setOnClickListener {
            isFlagAnimation = !isFlagAnimation

            val params = it.layoutParams as FrameLayout.LayoutParams

            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration = 2000L
            changeImageTransform.duration = 2000L

            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(changeImageTransform)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if (isFlagAnimation) {
                params.height = FrameLayout.LayoutParams.MATCH_PARENT
                binding.imageViewEarth.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = FrameLayout.LayoutParams.WRAP_CONTENT
                binding.imageViewEarth.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            binding.imageViewEarth.layoutParams = params
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EarthFragment()
    }

}