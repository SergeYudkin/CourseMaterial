package com.example.coursematerial.view.manyFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentSettingsBinding
import com.example.coursematerial.databinding.FragmentStartBinding
import com.example.coursematerial.view.api.*
import com.example.coursematerial.view.settings.SettingsFragment


class StartFragment: Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() {
            return _binding!!
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
        //TODO HW избавиться от R.layout.fragment_earth R.layout.fragment_mars R.layout.fragment_system через  .setImageResource()
        // ImageView(requireActivity()).setImageResource()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        var layout = R.layout.fragment_first
        arguments?.let {
            layout = when(it.getInt(BUNDLE_KEY)){
                FIRST_FRAGMENT->R.layout.fragment_first
                SECOND_FRAGMENT->R.layout.fragment_second
                THIRD_FRAGMENT->R.layout.fragment_third
                else->R.layout.fragment_first
            }

        }

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}