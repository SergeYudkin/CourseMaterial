package com.example.coursematerial.animation.ux

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentUxBinding


class UXFragment: Fragment() {


    private var _binding : FragmentUxBinding? = null
    private val binding : FragmentUxBinding
        get(){
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUxBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomNavigationViewUX()
    }


    private fun initBottomNavigationViewUX() {

        binding.bottomNavigationViewUx.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ux_text -> {
                    navigateTo(UxTextFragment())
                    true
                }
                R.id.ux_button -> {
                    navigateTo(UxButtonFragment())
                    true
                }
                R.id.ux_tutorial -> {
                    navigateTo(UxTutorialFragment())
                    true
                }
                else -> true
            }
        }
        binding.bottomNavigationViewUx.selectedItemId = R.id.ux_text


    }

   /* private fun navigationTo(f: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.ux_container, f).commit()
    }*/

    private fun navigateTo(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in,
            R.anim.fade_out,
            R.anim.fade_in,
            R.anim.slide_out
        ).replace(R.id.ux_container, fragment).commit()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = UXFragment()
    }

}