package com.mobdeve.phexplore

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class DestinationAdapter(private val data: ArrayList<DestinationModel>, private val orientationKey:Int) : RecyclerView.Adapter<DestinationViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinationViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.horizontal_recycler, parent, false)
        if (orientationKey == 1)
            view = LayoutInflater.from(parent.context).inflate(R.layout.vertical_recycler, parent, false)
        return DestinationViewHolder(view)
    }


    override fun onBindViewHolder(holder: DestinationViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }
    override fun getItemCount(): Int {
        return this.data.size
    }
}