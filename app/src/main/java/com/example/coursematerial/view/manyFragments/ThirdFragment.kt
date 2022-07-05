package com.example.coursematerial.view.manyFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentFirstBinding
import com.example.coursematerial.databinding.FragmentSecondBinding
import com.example.coursematerial.databinding.FragmentThirdBinding
import com.example.coursematerial.view.settings.SettingsFragment

import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.EpicViewModel
import com.example.coursematerial.viewmodel.MarsViewModel
import com.example.coursematerial.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

class ThirdFragment: Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding: FragmentThirdBinding
        get() {
            return _binding!!
        }

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        request()

    }

    private fun request() {
        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.marsSendServerRequest()


    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.SuccessMars -> {
                if (appState.marsServerResponseData.photos.isEmpty()){
                    Snackbar.make(binding.root, "В этот день curiosity не сделал ни одного снимка", Snackbar.LENGTH_SHORT).show()
                }else {
                    val url = appState.marsServerResponseData.photos.first().imgSrc
                    binding.imageViewThird.load(url)
                }
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
            ThirdFragment()
    }

}