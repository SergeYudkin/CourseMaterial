package com.example.coursematerial.view.settings




import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.coursematerial.R

import com.example.coursematerial.databinding.FragmentSettingsBinding

import com.example.coursematerial.view.api.*
import com.example.coursematerial.view.manyFragments.StartFragment


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}