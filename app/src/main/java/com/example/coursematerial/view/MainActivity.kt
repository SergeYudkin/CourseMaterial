package com.example.coursematerial.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursematerial.R
import com.example.coursematerial.pictureoftheday.PictureOfTheDayFragment
import com.example.coursematerial.utils.Parameters


class MainActivity : AppCompatActivity() {
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