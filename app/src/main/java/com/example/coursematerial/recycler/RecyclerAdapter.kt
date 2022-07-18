package com.example.coursematerial.recycler

import android.view.LayoutInflater
import android.view.ScrollCaptureCallback
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

class RecyclerAdapter(private var listData:MutableList<Data>,val callbackAdd: AddItem,val callbackRemove:RemoveItem): RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>() {


    fun setListDataRemove(listDataNew: MutableList<Data>,position: Int){
            listData = listDataNew
        notifyItemRemoved(position)
    }
    fun setListDataAdd(listDataNew: MutableList<Data>,position: Int){
        listData = listDataNew
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding):
        BaseViewHolder(binding.root){
       override fun bind(data: Data) {
            binding.textViewHeader.text = data.name
        }
    }
    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding):
        BaseViewHolder(binding.root){
        override fun bind(data: Data) {
            binding.textViewEarth.text = data.name
        }
    }


   inner class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding):
        BaseViewHolder(binding.root){
        override fun bind(data: Data) {
            binding.textViewMars.text = data.name

            binding.addItemImageView.setOnClickListener{
                callbackAdd.add(layoutPosition)

            }
            binding.removeItemImageView.setOnClickListener{
                callbackRemove.remove(layoutPosition)

            }
            binding.moveItemUp.setOnClickListener {
                // // TODO HW java.lang.IndexOutOfBoundsException: Index: -1, Size: 7
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition-1,this)
                    }
                notifyItemMoved(layoutPosition,layoutPosition-1)
            }
            binding.moveItemDown.setOnClickListener {
                // TODO HW java.lang.IndexOutOfBoundsException: Index: 8, Size: 7
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition+1,this)
                }
                notifyItemMoved(layoutPosition,layoutPosition+1)
            }

        }

    }



    abstract class BaseViewHolder(view: View):RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Data)
    }

}