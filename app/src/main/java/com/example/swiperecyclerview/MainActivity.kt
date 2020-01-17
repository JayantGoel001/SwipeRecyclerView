package com.example.swiperecyclerview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),RecyclerItemTouchHelperListener{
    lateinit var dummyList: ArrayList<User>
    lateinit var coordinateLayout:CoordinatorLayout
    lateinit var swipeRecyclerAdapter: SwipeRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(supportActionBar!=null)
        {
            supportActionBar!!.title="Swipeable"
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setHomeButtonEnabled(true)
        }
        dummyList=ArrayList<User>()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        swipeRecyclerAdapter=SwipeRecyclerAdapter(this,dummyList)
        recyclerView.adapter=swipeRecyclerAdapter

        val callback:ItemTouchHelper.SimpleCallback=RecyclerTouchHelper(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT, this)
        val itemTouchHelper=ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        coordinateLayout=findViewById<CoordinatorLayout>(R.id.swipeCoordinate)
        for (i in 0..16)
        {
            dummyList.add(User("Title","SubTitle"))
        }
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    override fun onSwipe(viewHolder: RecyclerView.ViewHolder, direction: Int, position: Int) {
        val name= dummyList[viewHolder.adapterPosition].title
        val deletedItem=dummyList[viewHolder.adapterPosition]
        val deletedIndex=viewHolder.adapterPosition
        swipeRecyclerAdapter.removeItems(viewHolder.adapterPosition)

        val snackbar:Snackbar= Snackbar.make(coordinateLayout,"Item Removed From List",Snackbar.LENGTH_LONG)
        snackbar.setAction("UNDO") {
            if(deletedIndex==0)
            {
                val layoutManager=recyclerView.layoutManager as LinearLayoutManager
                layoutManager.scrollToPosition(0)
            }
            swipeRecyclerAdapter.restoreItem(deletedItem,deletedIndex)
        }
        snackbar.setActionTextColor(Color.YELLOW)
        snackbar.show()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) :Boolean{
        return true
    }
}
