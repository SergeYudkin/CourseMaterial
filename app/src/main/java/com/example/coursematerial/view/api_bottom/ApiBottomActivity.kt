package com.example.coursematerial.view.api_bottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coursematerial.R
import com.example.coursematerial.databinding.ActivityApiBinding
import com.example.coursematerial.databinding.ActivityApiBottomBinding
import com.example.coursematerial.view.api.*
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ApiBottomActivity :AppCompatActivity() {

    lateinit var binding: ActivityApiBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupNavigation ()


    }

    private fun setupNavigation (){
        binding.bottomNavigationView.selectedItemId = R.id.action_bottom_view_mars

      val badge =   binding.bottomNavigationView.getOrCreateBadge(R.id.action_bottom_view_system)
        badge.number= 1000000
        badge.maxCharacterCount= 8  // колличество выводимых знаков
        badge.badgeGravity = BadgeDrawable.BOTTOM_START  // отображение снизу  слева. По умолчанию сверху справа.
        badge.clearNumber() // удаляет все значения, остаётся пустое поле
       // binding.bottomNavigationView.removeBadge(R.id.action_bottom_view_system)  // удаление badge//
    }

    private fun init(){
        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.action_bottom_view_earth->{
                    supportFragmentManager.beginTransaction().replace(R.id.container_bottom_navigation_view,EarthFragment()).commit()
                    true
                }

                R.id.action_bottom_view_mars->{
                    supportFragmentManager.beginTransaction().replace(R.id.container_bottom_navigation_view, MarsFragment()).commit()
                    true
                }
                R.id.action_bottom_view_system->{
                    supportFragmentManager.beginTransaction().replace(R.id.container_bottom_navigation_view, SystemFragment()).commit()
                    true
                }
            }
            true
        }
    }
}