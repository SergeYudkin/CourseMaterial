package com.example.coursematerial.view.manyFragments.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentMaterialBinding


class MaterialFragment: Fragment() {


    private var _binding : FragmentMaterialBinding? = null
    private val binding : FragmentMaterialBinding
        get(){
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMaterialBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigationView()
    }


    private fun initBottomNavigationView() {

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_constraint -> {
                    navigationTo(ConstraintFragment())
                    true
                }
                R.id.action_coordinator -> {
                    navigationTo(CoordinatorFragment())
                    true
                }
                R.id.action_motion -> {
                    //navigationTo(MotionFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_coordinator


    }

    private fun navigationTo(f: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MaterialFragment()
    }

}