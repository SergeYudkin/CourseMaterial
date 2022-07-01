package com.example.coursematerial.view.manyFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentFirstBinding
import com.example.coursematerial.view.api.*
import com.example.coursematerial.view.api.BaseFragment.Companion.FIRST_FRAGMENT
import com.example.coursematerial.view.api.BaseFragment.Companion.SECOND_FRAGMENT
import com.example.coursematerial.view.api.BaseFragment.Companion.THIRD_FRAGMENT
import com.example.coursematerial.view.settings.SettingsFragment
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.MarsViewModel


class StartFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = R.layout.fragment_start
        arguments?.let {
            layout = when(it.getInt(BUNDLE_KEY)){
                FIRST_FRAGMENT->R.layout.fragment_first
                SECOND_FRAGMENT->R.layout.fragment_second
                THIRD_FRAGMENT->R.layout.fragment_third
                else->R.layout.fragment_first
            }

        }
            return inflater.inflate(layout,container,false)
    }



    companion object {

       internal const val BUNDLE_KEY = "key"
        internal const val FIRST_FRAGMENT = 0
        internal const val SECOND_FRAGMENT = 1
        internal const val THIRD_FRAGMENT = 2


        @JvmStatic
        fun newInstance(type: Int): Fragment {
            return  StartFragment().apply {
                arguments = Bundle().apply {
                    putInt(BUNDLE_KEY,type)
                }
            }
        }

    }


}