package com.example.coursematerial.view.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.R

class BaseFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = R.layout.fragment_earth
        arguments?.let {
            layout = when (it.getInt(BUNDLE_KEY)) {
                EARTH_FRAGMENT -> R.layout.fragment_earth
                MARS_FRAGMENT -> R.layout.fragment_mars
                SYSTEM_FRAGMENT -> R.layout.fragment_system
                else -> R.layout.fragment_earth
            }
        }
        return inflater.inflate(layout, container, false)
    }


    companion object {

        internal const val BUNDLE_KEY = "key"
        internal const val FIRST_FRAGMENT = 0
        internal const val SECOND_FRAGMENT = 1
        internal const val THIRD_FRAGMENT = 2
        @JvmStatic
        fun newInstance(type: Int): Fragment {
          return  BaseFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_KEY,type)
                }
            }
        }

    }
}