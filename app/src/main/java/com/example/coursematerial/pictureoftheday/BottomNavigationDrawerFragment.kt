package com.example.coursematerial.pictureoftheday

import android.os.Bundle
import android.view.*
import com.example.coursematerial.R
import com.example.coursematerial.animation.*
import com.example.coursematerial.animation.ux.UXFragment
import com.example.coursematerial.databinding.BottomNavigationLayoutBinding
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
                R.id.navigationOne -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,AnimationFragment.newInstance()).addToBackStack("").commit()

                }
                R.id.navigationTwo -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,PausePlayFragment.newInstance()).addToBackStack("").commit()
                }
                R.id.navigationThird -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,ExplodeFragment.newInstance()).addToBackStack("").commit()
                }
                R.id.blockFour -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,BlockFourFragment.newInstance()).addToBackStack("").commit()
                }
                R.id.mix -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,MixFragment.newInstance()).addToBackStack("").commit()
                }
                R.id.elevation -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,ElevationFragment.newInstance()).addToBackStack("").commit()
                }

                R.id.splash -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,SplashFragment.newInstance()).addToBackStack("").commit()
                }

                R.id.ux -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container,UXFragment.newInstance()).addToBackStack("").commit()
                }
            }
            true
        }
    }

}