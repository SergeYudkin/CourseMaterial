package com.example.coursematerial.view.settings


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.R
import com.example.coursematerial.animation.PausePlayFragment.Companion.newInstance
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentSettingsBinding

import com.example.coursematerial.view.api.EarthFragment
import com.example.coursematerial.view.api.MarsFragment
import com.example.coursematerial.view.api.SystemFragment
import com.example.coursematerial.view.api.ViewPager2Adapter
import com.example.coursematerial.view.manyfragments.SecondFragment
import com.example.coursematerial.view.manyfragments.StartFragment
import com.example.coursematerial.view.manyfragments.ThirdFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SettingsFragment : ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager2.adapter = ViewPager2Adapter(this)

        TabLayoutMediator(binding.tabLayout,binding.viewPager2,object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = when (position) {
                    StartFragment.FIRST_FRAGMENT -> "First"
                    StartFragment.SECOND_FRAGMENT -> "Second"
                    StartFragment.THIRD_FRAGMENT -> "Third"
                    else -> "First"
                }

                tab.icon = when (position) {
                    StartFragment.FIRST_FRAGMENT -> resources.getDrawable(R.drawable.ic_earth)
                    StartFragment.SECOND_FRAGMENT ->  resources.getDrawable(R.drawable.ic_mars)
                    StartFragment.THIRD_FRAGMENT ->  resources.getDrawable(R.drawable.ic_system)
                    else ->  resources.getDrawable(R.drawable.ic_earth)
                }
            }
        }).attach()

        setupNavigation()
        init()
    }


    private fun setupNavigation() {
        binding.bottomNavigationView.selectedItemId = R.id.action_bottom_view_mars
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_bottom_view_system)
        badge.number = 1000
        badge.maxCharacterCount = 3
    }

    private fun init() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_bottom_view_earth -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EarthFragment.newInstance()).addToBackStack("").commit()
                    true
                }
                R.id.action_bottom_view_mars -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment.newInstance()).addToBackStack("").commit()
                    true
                }
                R.id.action_bottom_view_system -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SystemFragment.newInstance()).addToBackStack("").commit()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }

}