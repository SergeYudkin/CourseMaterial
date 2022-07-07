package com.example.coursematerial.view.api

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.coursematerial.view.manyFragments.FirstFragment


class ViewPager2Adapter(private val fragmentManager: Fragment):FragmentStateAdapter(fragmentManager) {



    override fun getItemCount(): Int {
            return ADAPTER_SIZE
    }


        override fun createFragment(position: Int): Fragment {
            return FirstFragment.newInstance()


        }



}

