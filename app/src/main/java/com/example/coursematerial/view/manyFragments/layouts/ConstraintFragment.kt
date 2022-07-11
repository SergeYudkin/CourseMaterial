package com.example.coursematerial.view.manyFragments.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.databinding.FragmentConstreintBinding


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

    var flag = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonThree.setOnClickListener {
            flag = !flag
            binding.groupOne.visibility = if (flag) View.VISIBLE else View.INVISIBLE
        }
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