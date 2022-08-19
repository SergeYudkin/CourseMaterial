package com.example.coursematerial.view.manyfragments.layouts

import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentMotionStartBinding

class MotionFragment : ViewBindingFragment<FragmentMotionStartBinding>(FragmentMotionStartBinding::inflate){


    companion object{
        @JvmStatic
        fun newInstance() = MotionFragment()
    }

}