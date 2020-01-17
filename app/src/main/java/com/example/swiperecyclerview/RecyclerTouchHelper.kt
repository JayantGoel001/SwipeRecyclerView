package com.example.swiperecyclerview

import android.graphics.Canvas
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecyclerTouchHelper(dragDirs:Int, swipeDirs:Int, private val recyclerItemTouchHelperListener: RecyclerItemTouchHelperListener): ItemTouchHelper.SimpleCallback(dragDirs,swipeDirs) {
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if(viewHolder.itemViewType!=target.itemViewType)
        {
            return false
        }
        recyclerItemTouchHelperListener.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if(viewHolder is SwipeRecyclerAdapter.ViewHolder)
        {
            recyclerItemTouchHelperListener.onSwipe(viewHolder,direction,viewHolder.adapterPosition)
        }
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        if (viewHolder is SwipeRecyclerAdapter.ViewHolder)
        {
            val foregroundView:View=(viewHolder).rel_foreground
            ItemTouchHelper.Callback.getDefaultUIUtil().onSelected(foregroundView)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if(viewHolder is SwipeRecyclerAdapter.ViewHolder)
        {
            val view:View=viewHolder.rel_foreground
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c,recyclerView,view,dX,dY,actionState,isCurrentlyActive)
        }
        if(viewHolder is SwipeRecyclerAdapter.ViewHolder)
        {
            val view:View=viewHolder.rel_foreground
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        if (viewHolder is SwipeRecyclerAdapter.ViewHolder)
        {
            val view:View=viewHolder.rel_foreground
            ItemTouchHelper.Callback.getDefaultUIUtil().clearView(view)
        }
    }

    override fun onChildDrawOver(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean)
    {
        if(viewHolder is SwipeRecyclerAdapter.ViewHolder) {
            val view = viewHolder.rel_foreground
            ItemTouchHelper.Callback.getDefaultUIUtil()
                .onDrawOver(c, recyclerView, view, dX, dY, actionState, isCurrentlyActive)
        }
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }
}
