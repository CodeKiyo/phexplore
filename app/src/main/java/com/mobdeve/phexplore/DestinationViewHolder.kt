package com.mobdeve.phexplore

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class DestinationViewHolder(val itemView:View): RecyclerView.ViewHolder(itemView) {
    private  val name: TextView = itemView.findViewById(R.id.destName)
    private val image: ImageView = itemView.findViewById(R.id.destImage)
    private val description: TextView = itemView.findViewById(R.id.destDescription)
    private val location: TextView = itemView.findViewById(R.id.destLocation)
    fun bindData(model: DestinationModel) {
        this.name.text = model.destName
        this.image.setImageResource(model.destImage)
        this.description.text = model.destDescription
        this.location.text = model.destCity
        this.itemView.setOnClickListener {
            val intentToViewItem = Intent(itemView.context, MenuItemViewActivity::class.java)
            intentToViewItem.putExtra("DEST_NAME", model.destName)
            intentToViewItem.putExtra("DEST_DESCRIPTION", model.destDescription)
            intentToViewItem.putExtra("DEST_IMAGE", model.destImage)
            intentToViewItem.putExtra("DEST_CITY", model.destCity)
            (itemView.context as? Activity)?.startActivity(intentToViewItem, null)
        }
    }
}