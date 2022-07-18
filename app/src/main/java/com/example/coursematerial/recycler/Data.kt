package com.example.coursematerial.recycler

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coursematerial.view.api.TYPE_MARS
import com.example.coursematerial.view.manyfragments.StartFragment



data class Data( val someText: String = "Text", val someDescription: String? = "Description", val type: Int = TYPE_MARS ){




}