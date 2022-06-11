package com.example.coursematerial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coursematerial.pictureoftheday.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*if (savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(com.google.android.material.R.id.container,PictureOfTheDayFragment.newInstance()).commit()
        }*/
    }
}