package com.example.coursematerial.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
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
        Data("Header",type = TYPE_HEADER),
        Data("Earth",type = TYPE_EARTH),
        Data("Earth",type = TYPE_EARTH),
        Data("Mars",type = TYPE_MARS),
        Data("Earth",type = TYPE_EARTH),
        Data("Earth",type = TYPE_EARTH),
        Data("Earth",type = TYPE_EARTH),
        Data("Earth",type = TYPE_EARTH),
        Data("Earth",type = TYPE_EARTH),
        Data("Mars",type = TYPE_MARS)
    )

    lateinit var adapter: RecyclerAdapter


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


    }

    private val callbackAdd = AddItem {
        data.add(it, Data("Mars(New)",type = TYPE_MARS))
        adapter.setListDataAdd(data,it)
    }
    private val callbackRemove = RemoveItem {
        data.removeAt(it)
        adapter.setListDataRemove(data,it)
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