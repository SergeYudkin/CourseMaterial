package com.example.coursematerial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentSettingsBinding
import com.example.coursematerial.pictureoftheday.PictureOfTheDayFragment
import com.example.coursematerial.utils.Parameters

import com.example.coursematerial.view.settings.SettingsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator



class MainActivity : AppCompatActivity() {
    lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Parameters.getInstance().theme)
        setContentView(R.layout.activity_main)

        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container,
                PictureOfTheDayFragment.newInstance()).commit()


        }

    }

}