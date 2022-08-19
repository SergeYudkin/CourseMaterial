package com.example.coursematerial.view.manyfragments.layouts


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentCoordinatorBinding


class CoordinatorFragment : ViewBindingFragment<FragmentCoordinatorBinding>(FragmentCoordinatorBinding::inflate){

    companion object{
        @JvmStatic
        fun newInstance() = CoordinatorFragment()
    }

}