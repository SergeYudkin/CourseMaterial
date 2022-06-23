package com.example.coursematerial.view.settings



import android.app.Activity
import android.content.Intent
import android.net.Uri
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

import java.util.*


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setContentView(R.layout.fragment_settings)

        if (savedInstanceState==null) {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.settings_container, StartFragment.newInstance(-1)
            ).commit()
        }
        binding.viewPager.adapter = ViewPager2Adapter(this)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
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