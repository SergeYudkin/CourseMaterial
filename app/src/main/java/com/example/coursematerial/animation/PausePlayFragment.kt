package com.example.coursematerial.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coursematerial.animation.ux.ViewBindingFragment
import com.example.coursematerial.databinding.FragmentPausePlayBinding


class PausePlayFragment: ViewBindingFragment<FragmentPausePlayBinding>(FragmentPausePlayBinding::inflate) {


    companion object {
        @JvmStatic
        fun newInstance() =
            PausePlayFragment()
    }

}