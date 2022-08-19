package com.example.coursematerial.view.manyfragments.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.coursematerial.R
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentMaterialBinding


class MaterialFragment: ViewBindingFragment<FragmentMaterialBinding>(FragmentMaterialBinding::inflate) {


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
                    navigationTo(MotionFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_motion

    }

    private fun navigationTo(f: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.material_container, f).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MaterialFragment()
    }

}