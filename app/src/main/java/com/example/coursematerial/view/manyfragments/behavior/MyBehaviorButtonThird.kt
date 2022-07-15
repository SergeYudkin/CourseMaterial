package com.example.coursematerial.view.manyfragments.behavior


/*class MyBehaviorButtonThird(context: Context, attrs: AttributeSet?=null): CoordinatorLayout.Behavior<Button>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        return (dependency.id == R.id.button_second)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: Button,
        dependency: View
    ): Boolean {
        if (dependency.id == R.id.button_second){
            child.x = dependency.y + child.width
            child.y = dependency.y

        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}*/