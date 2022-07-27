package com.example.coursematerial.recycler

import java.text.FieldPosition

interface ItemTouchHelperAdapter {

    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewHolder {

    fun onItemSelect()
    fun onItemClear()

}