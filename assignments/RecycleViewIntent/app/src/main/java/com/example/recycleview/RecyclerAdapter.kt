package com.example.recycleview
import android.content.Intent
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import java.io.File
import kotlin.random.Random


class RecyclerAdapter (titles: Array<String>,
                       details: Array<String>,
                       images: Array<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    var data = Data()
    private var titles = titles
    private var details = details
    private var images = images
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener { v: View ->
                val position: Int = getAdapterPosition() // gets the position of the item that's clicked upon
                val title: String = titles[position]
                val detail: String = details[position]
                val image: String = images[position]

                // https://stackoverflow.com/questions/42468113/how-can-i-use-getresources-inside-of-onbindviewholder
                // https://stackoverflow.com/questions/9403321/android-how-to-retrieve-file-name-and-extension-of-a-resource-by-resource-id
                val imageProps = TypedValue()
                this.itemImage.resources.getValue(image, imageProps, true)

                val i = Intent(v.context, SecondActivity::class.java)

                //val titleString = "hardcoded title"  // testing
                val titleString = title
                val detailsString = detail
                val imageString = image

                i.putExtra("tString", titleString) // put titleString into intent.extras as tString
                i.putExtra("dString", detailsString)
                i.putExtra("iString", imageString)

                //startActivity(i) // uses all the information attached to i to start the activity
                startActivity(v.context, i,null)

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