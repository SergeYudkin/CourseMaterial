package com.example.coursematerial.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coursematerial.databinding.FragmentRecyclerItemEarthBinding
import com.example.coursematerial.databinding.FragmentRecyclerItemHeaderBinding
import com.example.coursematerial.databinding.FragmentRecyclerItemMarsBinding
import com.example.coursematerial.view.api.TYPE_EARTH
import com.example.coursematerial.view.api.TYPE_MARS
import com.example.coursematerial.view.manyfragments.StartFragment

class RecyclerAdapter(private val listData:List<Data>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_EARTH ->{
                val binding = FragmentRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                 EarthViewHolder(binding)
            }
            TYPE_MARS ->{
                val binding = FragmentRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                 MarsViewHolder(binding)
            }
            else ->{
                val binding = FragmentRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                 HeaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding):RecyclerView.ViewHolder(binding.root){

    }

    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding):RecyclerView.ViewHolder(binding.root){

    }

    class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding):RecyclerView.ViewHolder(binding.root){

    }
}