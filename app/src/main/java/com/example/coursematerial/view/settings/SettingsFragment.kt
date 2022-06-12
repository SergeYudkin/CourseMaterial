package com.example.coursematerial.view.settings



import android.os.Bundle
import android.view.*
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.coursematerial.databinding.FragmentSettingsBinding
import java.util.*
class SettingsFragment : Fragment() {}



    private var _binding : FragmentSettingsBinding? = null
    private val binding : FragmentSettingsBinding
    get(){
        return _binding!!
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chip1.setOnClickListener {
            binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->

            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {


        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }
}