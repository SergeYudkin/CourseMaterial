package com.example.coursematerial.view.manyfragments.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.coursematerial.R


class MyBehaviorButtonSecond(context: Context, attrs: AttributeSet?=null): CoordinatorLayout.Behavior<Button>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        return (dependency.id == R.id.button_one)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.button_one){
            child.x = dependency.x + child.width
            child.y = dependency.y

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}