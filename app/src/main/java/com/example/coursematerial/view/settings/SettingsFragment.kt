package com.example.coursematerial.view.settings



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentSettingsBinding

import com.example.coursematerial.view.MainActivity
import com.example.coursematerial.view.api.*

import java.util.*


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!

        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}