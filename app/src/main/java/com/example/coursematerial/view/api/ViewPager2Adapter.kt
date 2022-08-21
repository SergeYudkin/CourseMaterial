package com.example.coursematerial.view.api

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coursematerial.view.manyfragments.FirstFragment
import com.example.coursematerial.view.manyfragments.SecondFragment
import com.example.coursematerial.view.manyfragments.ThirdFragment


class ViewPager2Adapter(private val fragmentManager: Fragment):FragmentStateAdapter(fragmentManager) {

    //private var mass = listOf (FirstFragment,SecondFragment,ThirdFragment)
    private val fragments = arrayOf(EarthFragment(),MarsFragment(),SystemFragment())

    override fun getItemCount(): Int {
            return fragments.size
    }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]


        }

}

