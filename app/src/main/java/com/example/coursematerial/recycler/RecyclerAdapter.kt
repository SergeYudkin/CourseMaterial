package com.example.coursematerial.recycler


import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentRecyclerItemEarthBinding
import com.example.coursematerial.databinding.FragmentRecyclerItemHeaderBinding
import com.example.coursematerial.databinding.FragmentRecyclerItemMarsBinding
import com.example.coursematerial.recycler.diffutil.Change
import com.example.coursematerial.recycler.diffutil.DiffUtilCallback
import com.example.coursematerial.recycler.diffutil.createCombinedPayload
import com.example.coursematerial.view.api.TYPE_EARTH
import com.example.coursematerial.view.api.TYPE_MARS

class RecyclerAdapter(private var listData:MutableList<Pair<Data,Boolean>>,val callbackAdd: AddItem,val callbackRemove:RemoveItem):
    RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(),ItemTouchHelperAdapter {


    fun setListDataForDiffUtil(listDataNew: MutableList<Pair<Data, Boolean>>){
      val diff =   DiffUtil.calculateDiff(DiffUtilCallback(listData, listDataNew))
        diff.dispatchUpdatesTo(this)
        listData = listDataNew
    }

    fun setListDataRemove(listDataNew: MutableList<Pair<Data,Boolean>>,position: Int){
            listData = listDataNew
        notifyItemRemoved(position)
    }
    fun setListDataAdd(listDataNew: MutableList<Pair<Data,Boolean>>,position: Int){
        listData = listDataNew
        notifyItemInserted(position)
    }

    override fun getItemViewType(position: Int): Int {
        return listData[position].first.type
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

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if(payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads)
        }else{
            val createCombinedPayload = createCombinedPayload(payloads as  List<Change<Pair<Data, Boolean>>>)
            if (createCombinedPayload.newData.first.name != createCombinedPayload.oldData.first.name) // в данном случае это лишнее
                holder.itemView.findViewById<TextView>(R.id.textViewMars).text = createCombinedPayload.newData.first.name



        }

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class HeaderViewHolder(val binding: FragmentRecyclerItemHeaderBinding):
        BaseViewHolder(binding.root){
       override fun bind(data: Pair<Data,Boolean>) {
            binding.textViewHeader.text = data.first.name
        }
    }
    class EarthViewHolder(val binding: FragmentRecyclerItemEarthBinding):
        BaseViewHolder(binding.root){
        override fun bind(data: Pair<Data,Boolean>) {
            binding.textViewEarth.text = data.first.name
        }
    }


   inner class MarsViewHolder(val binding: FragmentRecyclerItemMarsBinding):
        BaseViewHolder(binding.root){
        override fun bind(data: Pair<Data,Boolean>) {
            binding.textViewMars.text = data.first.name

            binding.addItemImageView.setOnClickListener{
                callbackAdd.add(layoutPosition)

            }
            binding.removeItemImageView.setOnClickListener{
                callbackRemove.remove(layoutPosition)

            }
            binding.moveItemUp.setOnClickListener {
                // // TODO HW java.lang.IndexOutOfBoundsException: Index: -1, Size: 7
                    listData.removeAt(layoutPosition).apply {
                        listData.add(layoutPosition-1,this)   //  сделать на  if/else вместо обработки ошибки
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
            binding.marsDescriptionTextView.visibility =
                if (listData[layoutPosition].second)
                    View.VISIBLE else View.GONE

            binding.marsImageView.setOnClickListener {
                listData[layoutPosition] = listData[layoutPosition].let {
                    it.first to !it.second
                }

                notifyItemChanged(layoutPosition)



            }

        }




   }



    abstract class BaseViewHolder(view: View):
        RecyclerView.ViewHolder(view),ItemTouchHelperViewHolder {

        abstract fun bind(data: Pair<Data,Boolean>)
        override fun onItemSelect() {
            itemView.setBackgroundColor(ContextCompat.getColor(itemView.context,R.color.blu_500))
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition,this)
        }
        notifyItemMoved(fromPosition,toPosition)
    }


    override fun onItemDismiss(position: Int) {
        callbackRemove.remove(position)
    }

}