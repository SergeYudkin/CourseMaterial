package com.example.coursematerial.pictureoftheday


import android.content.Intent
import android.net.Uri
import android.util.Log
import android.os.Bundle
import android.view.*
import android.widget.Toast
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.coursematerial.view.MainActivity
import com.example.coursematerial.databinding.FragmentPictureOfTheDayBinding
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.PictureOfTheDayViewModel
import retrofit2.http.Url
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var _binding : FragmentPictureOfTheDayBinding? = null
    private val binding : FragmentPictureOfTheDayBinding
    get(){
        return _binding!!
    }

    private val viewModel: PictureOfTheDayViewModel by lazy{
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPictureOfTheDayBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request()
        clickWiki()


    }

    private fun clickWiki(){

        binding.inputLayout.setEndIconOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

    }

    private fun request(){
        viewModel.getLiveDataForViewToObserve().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendServerRequest()
    }

    private fun renderData(appState: AppState){
        when(appState){
            is AppState.Error -> {/*TODO()*/}
            is AppState.Loading -> {/*TODO()*/}
            is AppState.Success -> {
                binding.imageView.load(appState.serverResponseData.hdurl){
                    // дз placehilde+error+transform
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
            PictureOfTheDayFragment()
    }
}