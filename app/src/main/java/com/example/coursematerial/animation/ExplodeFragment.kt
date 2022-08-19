package com.example.coursematerial.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.Explode
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentAnimationStartBinding
import com.example.coursematerial.databinding.FragmentExplodeBinding


class ExplodeFragment: ViewBindingFragment<FragmentExplodeBinding>(FragmentExplodeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = Adapter()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExplodeFragment()
    }

    inner class Adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fragment_animation_explode_list_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {

                val rect = Rect()
                it.getGlobalVisibleRect(rect)
                val explode = Explode()
                explode.duration = 2000L
                explode.epicenterCallback = object : Transition.EpicenterCallback() {
                    override fun onGetEpicenter(transition: Transition): Rect {
                        return rect
                    }
                }
                TransitionManager.beginDelayedTransition(binding.recyclerView, explode)
                binding.recyclerView.adapter = null
            }

        }

        override fun getItemCount(): Int {
            return 28
        }

        inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    }

}