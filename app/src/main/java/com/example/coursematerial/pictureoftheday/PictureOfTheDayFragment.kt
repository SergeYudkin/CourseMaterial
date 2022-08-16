package com.example.coursematerial.pictureoftheday


import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.*
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.text.style.ImageSpan
import android.text.style.TypefaceSpan
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.provider.FontRequest
import androidx.core.provider.FontsContractCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.*
import coil.load
import com.example.coursematerial.R
import com.example.coursematerial.R.drawable
import com.example.coursematerial.R.menu
import com.example.coursematerial.databinding.FragmentPictureOfTheDayBinding

import com.example.coursematerial.recycler.RecyclerFragment
import com.example.coursematerial.utils.Parameters
import com.example.coursematerial.view.MainActivity
import com.example.coursematerial.view.manyfragments.layouts.MaterialFragment
import com.example.coursematerial.view.settings.SettingsFragment
import com.example.coursematerial.viewmodel.AppState
import com.example.coursematerial.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var isFlag = false
    private var isMain = true
    private var duration = 2000L
    private var isFlagAnimation = false
    lateinit var spannableRainbow: SpannableString

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
            R.id.app_bar_const -> {requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, MaterialFragment.newInstance()).addToBackStack("").commit()}


            R.id.app_bar_fav -> {requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container, RecyclerFragment.newInstance()).addToBackStack("").commit()}

            R.id.app_bar_settings -> {requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container,SettingsFragment.newInstance()).addToBackStack("").commit()}
            android.R.id.home-> {
                BottomNavigationDrawerFragment().show(requireActivity().supportFragmentManager,"")

            }


        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        clickButtonStyle()
        clickChip()
        switchNightMode()
        actionBar()
        request()
        clickWiki()
        BSB ()
        FAB()
        clickPic()

    }

    private fun clickPic(){
        isFlagAnimation = !isFlagAnimation

        binding.imageView.setOnClickListener {
            isFlagAnimation = !isFlagAnimation

            val params = it.layoutParams as LinearLayout.LayoutParams

            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration
            changeImageTransform.duration

            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER

            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(changeImageTransform)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if (isFlagAnimation) {
                params.height = LinearLayout.LayoutParams.MATCH_PARENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = LinearLayout.LayoutParams.WRAP_CONTENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            binding.imageView.layoutParams = params
        }
    }


    private fun takeDate(count: Int): String {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.DAY_OF_MONTH, count)
        val format1 = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format1.timeZone = TimeZone.getTimeZone("EST")
        return format1.format(currentDate.time)
    }

    private fun clickButtonStyle() {


        binding.buttonBlu.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyBlueTheme
            requireActivity().recreate()

        }

        binding.buttonRed.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyRedTheme
            requireActivity().recreate()

        }

        binding.buttonGreen.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyGreenTheme
            requireActivity().recreate()
        }

    }

    private fun switchNightMode(){
        requireActivity().findViewById<SwitchMaterial>(R.id.switch_night_mode).setOnCheckedChangeListener{buttonView,isChecked->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

        private fun clickChip(){
                isFlag = !isFlag


            binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){

                    R.id.yesterday ->{viewModel.sendServerRequest(takeDate(-1))
                        binding.imageView.visibility = if (isFlag)View.VISIBLE else{View.GONE}
                    }
                    R.id.day_before_yesterday->{viewModel.sendServerRequest(takeDate(-2))
                        binding.imageView.visibility = if (isFlag)View.VISIBLE else{View.GONE}
                    }
                    R.id.today ->{viewModel.sendServerRequest()
                        binding.imageView.visibility = if (isFlag)View.VISIBLE else{View.GONE}
                    }

                }
            }
        }

    private fun FAB(){

        binding.fab.setOnClickListener {
            isMain = !isMain


            if (!isMain) {
                ObjectAnimator.ofFloat(binding.fab,View.ROTATION,0f,675f).setDuration(duration).start()
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
               // binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawable.ic_back_fab))
                binding.bottomAppBar.replaceMenu(menu.menu_bottom_bar_other)
            } else {
                ObjectAnimator.ofFloat(binding.fab,View.ROTATION,675f,0f).setDuration(duration).start()
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



    @SuppressLint("NewApi") // TODO HW не потерять пользователей с 24-27 sdk версии
    private fun renderData(appState: AppState){

        val mayTran = TransitionSet()
        mayTran.ordering = TransitionSet.ORDERING_TOGETHER

        val fade = Fade()
        val changeImageTransform = ChangeImageTransform()
       // mayTran.addTransition(fade)
        mayTran.addTransition(changeImageTransform)
        mayTran.duration = 2000L

        TransitionManager.beginDelayedTransition(binding.root,mayTran)

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

                binding.textViewSpan.text = appState.serverResponseData.explanation

                spannableRainbow = SpannableString(appState.serverResponseData.explanation)
                rainbow(1)

                /*binding.textViewSpan.text = appState.serverResponseData.explanation
                binding.textViewSpan.typeface = Typeface.createFromAsset(requireActivity().assets,"aZeret1.ttf")

                    val spanned: Spanned
                    val spannableString: SpannableString
                    var spannableStringBuilder: SpannableStringBuilder

                val text = "My text \nbullet one \nbullet two"+
                        "\n"+
                        "bullet two\n"+
                        "bullet two\n"+
                        "bullet two\n"+
                        "bullet two\n"+
                        "bullet two"

                spannableStringBuilder = SpannableStringBuilder(text)

                binding.textViewSpan.setText(spannableStringBuilder, TextView.BufferType.EDITABLE)
                spannableStringBuilder = binding.textViewSpan.text as SpannableStringBuilder

                val result = text.indexesOf("\n")  // функция которую нужно запомнить

                var current = result.first()

                result.forEach{
                    if (current!=it){
                        spannableStringBuilder.setSpan(BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.blu_800),20),
                            current+1,it,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                    }
                    current = it
                }
                spannableStringBuilder.setSpan(BulletSpan(20,ContextCompat.getColor(requireContext(),R.color.blu_800),20),
                    current+1,text.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

                Log.d("QQQ", result.toString())


                for (i in text.indices){
                    if (text[i]== 't'){
                        spannableStringBuilder.setSpan(
                            ForegroundColorSpan(ContextCompat.getColor(requireContext(),R.color.red)),
                        i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                val bitmap = ContextCompat.getDrawable(requireContext(),
                    R.drawable.ic_system
                )!!.toBitmap()
                for (i in text.indices){
                    if (text[i]== 'o'){
                        spannableStringBuilder.setSpan(
                            ImageSpan(bitmap),
                            i,i+1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }
                }

                spannableStringBuilder.insert(3,"word")
                //spannableStringBuilder.replace(3,4,"word")
               // binding.textViewSpan.text = spannableStringBuilder


                val request = FontRequest("com.google.android.gms.fonts","com.google.android.gms", "Aladin",
                    R.array.com_google_android_gms_fonts_certs)

                val callback = object :FontsContractCompat.FontRequestCallback(){
                    override fun onTypefaceRetrieved(typeface: Typeface?) {

                        typeface?.let {
                            spannableStringBuilder.setSpan(
                                TypefaceSpan(it),
                                0,spannableStringBuilder.length,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                        }

                        super.onTypefaceRetrieved(typeface)
                    }
                }

                FontsContractCompat.requestFont(requireContext(),request,callback, Handler(Looper.getMainLooper()))*/

            }
        }
    }

    fun rainbow(i:Int=1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount>5) 1 else currentCount
            }
            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }

    private fun colorText(colorFirstNumber:Int){
        binding.textViewSpan.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = binding.textViewSpan.text as SpannableString
        val map = mapOf(
            0 to ContextCompat.getColor(requireContext(), R.color.colorAccent),
            1 to ContextCompat.getColor(requireContext(), R.color.orange),
            2 to ContextCompat.getColor(requireContext(), R.color.yellow),
            3 to ContextCompat.getColor(requireContext(), R.color.green),
            4 to ContextCompat.getColor(requireContext(), R.color.blue),
            5 to ContextCompat.getColor(requireContext(), R.color.purple_700),
            6 to ContextCompat.getColor(requireContext(),R.color.purple_500)
        )
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }

        var colorNumber = colorFirstNumber
        for (i in 0 until binding.textViewSpan.text.length) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1
            spannableRainbow.setSpan(
                ForegroundColorSpan(map.getValue(colorNumber)),
                i, i + 1,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
        }
    }

   /* fun String.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> =
        (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
            .findAll(this).map { it.range.first }.toList()*/


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