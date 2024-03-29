package com.mobdeve.phexplore


import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FilterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val filter_name: TextView = itemView.findViewById(R.id.filter_name)
    private val filter_image: ImageView = itemView.findViewById(R.id.filter_icon)
    private val filter_cardcolor: CardView = itemView.findViewById(R.id.filterCardView)

    fun bindData(model: FilterModel) {
        this.filter_name.text = model.filterName
        this.filter_image.setImageResource(model.filterImage)
        this.filter_cardcolor.backgroundTintList = ColorStateList.valueOf(Color.parseColor(model.filterColor))
        this.itemView.setOnClickListener {
        }
    }
}