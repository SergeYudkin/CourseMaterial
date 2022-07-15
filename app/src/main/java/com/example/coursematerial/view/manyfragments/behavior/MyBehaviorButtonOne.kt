package com.example.coursematerial.view.manyfragments.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


class MyBehaviorButtonOne(context: Context, attrs: AttributeSet?=null): CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        //return (dependency.id == R.id.bottomSheetContainer)
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout){
            child.y = dependency.y + dependency.height-child.height/2
            child.x =(dependency.width - child.width).toFloat()

            child.alpha = 1 - (abs(dependency.y)/(dependency.height/2))

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}