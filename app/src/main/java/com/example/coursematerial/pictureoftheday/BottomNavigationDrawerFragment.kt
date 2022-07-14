package com.example.coursematerial.pictureoftheday

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.example.coursematerial.R
import com.example.coursematerial.animation.AnimationFragment
import com.example.coursematerial.databinding.BottomNavigationLayoutBinding
import com.example.coursematerial.view.manyFragments.layouts.MaterialFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomNavigationDrawerFragment: BottomSheetDialogFragment() {


    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomNavigationLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_navigation,menu)

    }


            override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,AnimationFragment.newInstance()).addToBackStack("").commit()

                }
                R.id.navigation_two -> {}
            }
            true
        }
    }

}