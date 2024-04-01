package com.example.recycleview
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import android.content.Intent
import androidx.core.content.ContextCompat
import kotlin.random.Random



class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    var data = Data()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                Snackbar.make(v, "Click detected on item $position",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show()
                val i = Intent(v.getContext(),SecondActivity::class.java)
                ContextCompat.startActivity(v.context, i, null)

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        //code i need
        val rand = Random.nextInt(data.getTitleSize())
        viewHolder.itemTitle.text = data.titles[rand]
        viewHolder.itemDetail.text = data.details[rand]
        viewHolder.itemImage.setImageResource(data.images[rand])

    }
    override fun getItemCount(): Int {
        return data.titles.size
    }

}