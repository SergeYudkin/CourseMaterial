package com.example.coursematerial.view.manyfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.BuildConfig
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentSecondBinding
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.EpicViewModel

class SecondFragment: ViewBindingFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {


    private val viewModel: EpicViewModel by lazy {
        ViewModelProvider(this).get(EpicViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        request()

    }

    private fun request() {
        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.epicSendServerRequest()
        
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.SuccessEpic -> {
               // binding.imageViewSecond.load(appState.epicServerResponseData.last().identifier)
                val strDate = appState.epicServerResponseData.last().date.split("").first()
                val image = appState.epicServerResponseData.last().image
                val url = "https://api.nasa.gov/EPIC/archive/natural/" +
                        strDate.replace("-", "/", true) +
                        "/png/" +
                        "$image" +
                        ".png?api_key=${BuildConfig.NASA_API_KEY}"
                binding.imageViewSecond.load(url)
            }


        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SecondFragment()
    }

}