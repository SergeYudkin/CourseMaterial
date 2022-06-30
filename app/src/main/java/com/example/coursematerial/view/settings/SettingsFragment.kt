package com.example.coursematerial.view.settings




import android.os.Bundle
import android.view.*
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import com.example.coursematerial.R

import com.example.coursematerial.databinding.FragmentSettingsBinding

import com.example.coursematerial.view.api.*
import com.example.coursematerial.view.manyFragments.StartFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


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

        /*binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)*/

        binding.viewPager2.adapter = ViewPager2Adapter(this)

        TabLayoutMediator(binding.tabLayout,binding.viewPager2,object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                tab.text = when (position) {
                    StartFragment.FIRST_FRAGMENT -> "First"
                    StartFragment.SECOND_FRAGMENT -> "Second"
                    StartFragment.THIRD_FRAGMENT -> "Third"
                    else -> "Earth"
                }

                tab.icon = when (position) {
                    StartFragment.FIRST_FRAGMENT -> resources.getDrawable(R.drawable.ic_earth)
                    StartFragment.SECOND_FRAGMENT ->  resources.getDrawable(R.drawable.ic_mars)
                    StartFragment.THIRD_FRAGMENT ->  resources.getDrawable(R.drawable.ic_system)
                    else ->  resources.getDrawable(R.drawable.ic_earth)
                }
            }
        }).attach()

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