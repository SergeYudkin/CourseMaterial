package com.example.coursematerial.view.manyfragments.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentConstreintBinding


class ConstraintFragment: ViewBindingFragment<FragmentConstreintBinding>(FragmentConstreintBinding::inflate) {


    var flag = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonThree.setOnClickListener {
            flag = !flag
            binding.groupOne.visibility = if (flag) View.VISIBLE else View.INVISIBLE
        }
    }

    companion object{
        @JvmStatic
        fun newInstance() = ConstraintFragment
    }

}