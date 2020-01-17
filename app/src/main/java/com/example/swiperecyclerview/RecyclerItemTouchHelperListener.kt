package com.example.swiperecyclerview

import androidx.recyclerview.widget.RecyclerView

interface RecyclerItemTouchHelperListener {
    fun onSwipe(viewHolder:RecyclerView.ViewHolder,direction:Int,position:Int)
    fun onItemMove(fromPosition:Int,toPosition:Int):Boolean
}