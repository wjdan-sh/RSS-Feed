package com.example.rssfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssfeed.R.layout.item_row
import kotlinx.android.synthetic.main.item_row.view.*

class RVAdapter (private val result: MutableList<Movies>?) : RecyclerView.Adapter<RVAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                item_row
                ,parent
                ,false
            )
        )

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name =result!! [position]

        holder.itemView.apply{
            tv1.text= name.title
            tv2.text=name.description

        }

    }
    override fun getItemCount() : Int = result!!.size

}