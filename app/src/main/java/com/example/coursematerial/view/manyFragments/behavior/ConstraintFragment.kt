package com.example.coursematerial.view.manyFragments.behavior

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentConstreintBinding
import com.example.coursematerial.databinding.FragmentPictureOfTheDayBinding


class ConstraintFragment: Fragment() {

    private var _binding : FragmentConstreintBinding? = null
    private val binding : FragmentConstreintBinding
        get(){
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstreintBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        @JvmStatic
        fun newInstance() = ConstraintFragment
    }

}