package com.example.coursematerial.animation

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Explode
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.example.coursematerial.R
import com.example.coursematerial.databinding.FragmentAnimationBinding


class AnimationFragment: Fragment() {

    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root

    }
        var isFlagAnimation = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


       /* binding.animateButtonOne.setOnClickListener{
            isFlagAnimation = !isFlagAnimation

            val myAutoTransition = TransitionSet()
            //myAutoTransition.ordering = TransitionSet.ORDERING_TOGETHER
            myAutoTransition.ordering = TransitionSet.ORDERING_SEQUENTIAL

            val slide = Slide(Gravity.END)
            slide.duration = 800L
            val changeBounds = ChangeBounds()
            changeBounds.duration = 400L
           // myAutoTransition.duration = 1500L  dt
            myAutoTransition.addTransition(slide)
            myAutoTransition.addTransition(changeBounds)

            TransitionManager.beginDelayedTransition(binding.transitionContainer,myAutoTransition)
            binding.animateText.visibility = if (isFlagAnimation) View.VISIBLE else{
               View.GONE
            }
        }*/

        binding.recyclerView.adapter = Adapter()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AnimationFragment()
    }

    inner class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return MyViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.fragment_animation_explode_list_item,parent,false
                ) as View

            )
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{

           /* val rect = Rect(it.x.toInt(),it.y.toInt(),
            it.x.toInt() + it.width.toInt(),
            it.x.toInt()+ it.height)*/

            val rect = Rect()
            it.getGlobalVisibleRect(rect)
            val explode = Explode()
            explode.duration = 1500L
            explode.epicenterCallback = object : Transition.EpicenterCallback(){
                override fun onGetEpicenter(transition: Transition): Rect {
                    return rect
                }

            }
           // TransitionManager.beginDelayedTransition(binding.transitionContainer,explode)
            TransitionManager.beginDelayedTransition(binding.recyclerView,explode)
            binding.recyclerView.adapter = null
         }

        }

        override fun getItemCount(): Int {
            return 28
        }

       inner class MyViewHolder(view:View): RecyclerView.ViewHolder(view)

    }

}