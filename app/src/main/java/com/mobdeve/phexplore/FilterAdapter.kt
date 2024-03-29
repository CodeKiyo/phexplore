package com.mobdeve.phexplore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilterAdapter(private val data: ArrayList<FilterModel>) : RecyclerView.Adapter<FilterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        val inflatedView = itemView.inflate(R.layout.filter_recycler, parent, false)

        return FilterViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}