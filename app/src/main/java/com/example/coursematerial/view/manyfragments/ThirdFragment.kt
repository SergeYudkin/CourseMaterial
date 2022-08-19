package com.example.coursematerial.view.manyfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentThirdBinding
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.MarsViewModel
import com.google.android.material.snackbar.Snackbar

class ThirdFragment: ViewBindingFragment<FragmentThirdBinding>(FragmentThirdBinding::inflate) {

    private val viewModel: MarsViewModel by lazy {
        ViewModelProvider(this).get(MarsViewModel::class.java)
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

    companion object {
        @JvmStatic
        fun newInstance() =
            ThirdFragment()
    }

}