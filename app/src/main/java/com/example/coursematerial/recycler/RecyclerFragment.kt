package com.example.coursematerial.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentAnimationStartBinding
import com.example.coursematerial.databinding.FragmentRecyclerBinding
import com.example.coursematerial.view.api.TYPE_EARTH
import com.example.coursematerial.view.api.TYPE_HEADER
import com.example.coursematerial.view.api.TYPE_MARS


class RecyclerFragment: Fragment() {


    val data = arrayListOf(
        Pair(Data( 0,"Header",type = TYPE_HEADER),false),
        Pair(Data( 1,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 2,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 3,"Mars",type = TYPE_MARS),false),
        Pair(Data( 4,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 5,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 6,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 7,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 8,"Earth",type = TYPE_EARTH),false),
        Pair(Data( 9,"Mars",type = TYPE_MARS),false)
    )

    lateinit var adapter: RecyclerAdapter
    private var isNewList = false


    private var _binding: FragmentRecyclerBinding? = null
    private val binding: FragmentRecyclerBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = RecyclerAdapter(data, callbackAdd, callbackRemove )
        binding.recyclerView.adapter = adapter

       ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)

        binding.recyclerFAB.setOnClickListener{
            changeAdapterData()
        }
    }

    private val callbackAdd = AddItem {
        data.add(it, Pair(Data(0,"Mars(New)",type = TYPE_MARS),false))
        adapter.setListDataAdd(data,it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data,it)
    }

    private fun changeAdapterData() {
        adapter.setListDataForDiffUtil(createItemList(isNewList).map { it }.toMutableList())
        isNewList = !isNewList
    }


    private fun createItemList(instanceNumber: Boolean): List<Pair<Data, Boolean>>{
        return when(instanceNumber){
            false -> listOf(
                Pair(Data( 0,"Header",type = TYPE_HEADER),false),
                Pair(Data( 1,"Mars",type = TYPE_MARS),false),
                Pair(Data( 2,"Mars",type = TYPE_MARS),false),
                Pair(Data( 3,"Mars",type = TYPE_MARS),false),
                Pair(Data( 4,"Mars",type = TYPE_MARS),false),
                Pair(Data( 5,"Mars",type = TYPE_MARS),false),
                Pair(Data( 6,"Mars",type = TYPE_MARS),false),
                Pair(Data( 7,"Mars",type = TYPE_MARS),false),
                Pair(Data( 8,"Mars",type = TYPE_MARS),false),
                Pair(Data( 9,"Mars",type = TYPE_MARS),false)
            )
            true -> listOf (Pair(Data( 0,"Header",type = TYPE_HEADER),false),
            Pair(Data( 1,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 2,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 3,"Mars",type = TYPE_MARS),false),
            Pair(Data( 4,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 5,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 6,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 7,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 8,"Earth",type = TYPE_EARTH),false),
            Pair(Data( 9,"Mars",type = TYPE_MARS),false)
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecyclerFragment()
    }



}