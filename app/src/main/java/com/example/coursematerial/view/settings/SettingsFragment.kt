package com.example.coursematerial.view.settings




import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import com.example.coursematerial.R

import com.example.coursematerial.databinding.FragmentSettingsBinding

import com.example.coursematerial.view.MainActivity
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

       requireActivity(). supportFragmentManager.beginTransaction()
           .replace(R.id.settings_container,StartFragment.newInstance(0)).commit()




        /*requireActivity().setContentView(R.layout.fragment_settings)

        binding.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        //binding.viewPager.adapter = ViewPager2Adapter(this)*/


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