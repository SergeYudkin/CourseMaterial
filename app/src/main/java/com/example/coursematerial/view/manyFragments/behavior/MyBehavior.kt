package com.example.coursematerial.view.manyFragments.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.coursematerial.R


class MyBehavior(context: Context, attrs: AttributeSet?=null): CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency.id == R.id.bottomSContainer)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.bottomSContainer){
            child.y = dependency.y-200
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}