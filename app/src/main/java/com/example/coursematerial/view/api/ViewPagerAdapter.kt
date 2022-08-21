package com.example.coursematerial.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.coursematerial.view.manyfragments.StartFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

   private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

    override fun getCount(): Int {
       return fragments.size
       // return ADAPTER_SIZE
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position){
            StartFragment.FIRST_FRAGMENT -> "First"
            StartFragment.SECOND_FRAGMENT -> "Second"
            StartFragment.THIRD_FRAGMENT -> "Third"
            else -> "First"
        }
    }

    override fun getItem(position: Int): Fragment {
            return fragments[position]

    }
}