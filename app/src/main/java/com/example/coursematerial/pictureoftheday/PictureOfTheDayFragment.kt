package com.example.coursematerial.pictureoftheday


import android.app.Application
import android.content.Intent

import android.net.Uri
import android.util.Log
import android.os.Bundle
import android.provider.CalendarContract
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
import com.example.coursematerial.R
import com.example.coursematerial.R.*
import com.example.coursematerial.databinding.FragmentPictureOfTheDayBinding
import com.example.coursematerial.view.MainActivity

import com.example.coursematerial.pictureoftheday.PictureOfTheDayFragment.Companion.newInstance
import com.example.coursematerial.utils.Parameters
import com.example.coursematerial.view.settings.SettingsFragment
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import retrofit2.http.Url
import java.text.SimpleDateFormat

import java.util.*

class PictureOfTheDayFragment : Fragment() {


    private var isMain = true

    private var _binding : FragmentPictureOfTheDayBinding? = null
    private val binding : FragmentPictureOfTheDayBinding
    get(){
        return _binding!!
    }

    private val viewModel: PictureOfTheDayViewModel by lazy{
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPictureOfTheDayBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.app_bar_fav -> Toast.makeText(context, "Favourite", Toast.LENGTH_SHORT).show()
            R.id.app_bar_settings -> requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,SettingsFragment.newInstance()).commit()
            android.R.id.home-> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager,"")
            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        clickRadio()

        clickChip()

        actionBar()
        request()
        clickWiki()
        BSB ()
        FAB()


    }

    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }

    private fun clickRadio() {
        binding.radioGroup.setOnCheckedChangeListener{group, checkedId ->
            when(checkedId){
                R.id.radio_button_blu->{Parameters.getInstance().theme = R.style.MyBlueTheme
                requireActivity().recreate()}
                R.id.radio_button_red->{Parameters.getInstance().theme = R.style.MyRedTheme
                    requireActivity().recreate()}
                R.id.radio_button_green->{Parameters.getInstance().theme = R.style.MyGreenTheme
                    requireActivity().recreate()}
            }

        }
    }

        private fun clickChip(){
            binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    R.id.yesterday ->{viewModel.sendServerRequest(takeDate(-1))}
                    R.id.day_before_yesterday->{viewModel.sendServerRequest(takeDate(-2))}
                    R.id.today ->{viewModel.sendServerRequest()}

                }
            }
        }

    fun FAB(){
        binding.fab.setOnClickListener {
            isMain = !isMain
            if (!isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawable.ic_back_fab))
                binding.bottomAppBar.replaceMenu(menu.menu_bottom_bar_other)
            } else {
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(requireContext(),
                    drawable.ic_hamburger_menu_bottom_bar)

                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(menu.menu_bottom_bar)
            }

        }
    }

    private fun actionBar(){
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
    }

    private fun BSB (){
        val params = (binding.lifeHack.bottomSheetContainer.layoutParams as CoordinatorLayout.LayoutParams)
        val behavior =  params.behavior as BottomSheetBehavior
        behavior.state = BottomSheetBehavior.STATE_HIDDEN
        behavior. addBottomSheetCallback(object:BottomSheetBehavior
        .BottomSheetCallback(){
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){

                    /*   BottomSheetBehavior.STATE_COLLAPSED -> { TODO() }
                       BottomSheetBehavior.STATE_DRAGGING -> { TODO() }
                       BottomSheetBehavior.STATE_EXPANDED -> { TODO() }
                       BottomSheetBehavior.STATE_HALF_EXPANDED -> { TODO() }
                       BottomSheetBehavior.STATE_HIDDEN -> { TODO() }
                       BottomSheetBehavior.STATE_SETTLING -> { TODO() }*/
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                Log.d("@@@","$slideOffset slideOffset")
            }

        })
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
            is AppState.Error -> {}
            is AppState.Loading -> {
                 BottomSheetBehavior.STATE_HIDDEN
                binding.imageView.load(drawable.loadingfast)
            }
            is AppState.Success -> {

                BottomSheetBehavior.STATE_HIDDEN
                binding.imageView.load(appState.serverResponseData.hdurl){
                  error(drawable.youarestupidstupid)
                    placeholder(drawable.loadingfast)
                }

                binding.lifeHack.title.text = appState.serverResponseData.title
                binding.lifeHack.explanation.text = appState.serverResponseData.explanation

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