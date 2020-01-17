package com.example.swiperecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SwipeRecyclerAdapter(private var list: ArrayList<User>) :RecyclerView.Adapter<SwipeRecyclerAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item_swipe_recycler,parent,false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtTitle.text=list[position].title
        holder.txtSubTitle.text=list[position].subTitle

    }
    fun removeItems(position: Int)
    {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
    fun restoreItem(item:User,position: Int)
    {
        list.add(position,item)
        notifyItemInserted(position)
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)
    {
        //val imgCircle=itemView.findViewById<ImageView>(R.id.imgCircle)
        val txtTitle=itemView.findViewById<TextView>(R.id.txtSwipeTitle)
        val txtSubTitle=itemView.findViewById<TextView>(R.id.txtSwipeSubTitle)
        val rel_foreground=itemView.findViewById<RelativeLayout>(R.id.rel_foreground)
        val rel_background=itemView.findViewById<RelativeLayout>(R.id.rel_background)

    }
}
