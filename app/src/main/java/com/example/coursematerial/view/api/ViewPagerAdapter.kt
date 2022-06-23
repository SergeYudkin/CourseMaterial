package com.example.coursematerial.view.api

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.coursematerial.R
import com.example.coursematerial.view.manyFragments.StartFragment
import com.example.coursematerial.view.settings.SettingsFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

  //  val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

    override fun getCount(): Int {
        //return fragments.size
        return ADAPTER_SIZE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            FIRST_FRAGMENT -> "First"
            MARS_FRAGMENT -> "Mars"
            SYSTEM_FRAGMENT -> "System"
            else -> "Earth"
        }
    }

    override fun getItem(position: Int): Fragment {
            return StartFragment.newInstance(position)

    }
}