package com.example.coursematerial.view.manyFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.R
import com.example.coursematerial.view.api.*
import com.example.coursematerial.view.settings.SettingsFragment
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class StartFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = R.layout.fragment_first
        //TODO HW избавиться от R.layout.fragment_earth R.layout.fragment_mars R.layout.fragment_system через  .setImageResource()
        // ImageView(requireActivity()).setImageResource()
        arguments?.let {
            layout = when (it.getInt(BUNDLE_KEY)) {
                EARTH_FRAGMENT -> R.layout.fragment_earth
                MARS_FRAGMENT -> R.layout.fragment_mars
                SYSTEM_FRAGMENT -> R.layout.fragment_system
                else -> R.layout.fragment_first
            }
        }
        return inflater.inflate(layout, container, false)
    }


    companion object {
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