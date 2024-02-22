package com.mobdeve.phexplore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class MainViewAdapter(private val data: ArrayList<DestinationModel>) : RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val viewBinding: MenuitemLayoutBinding = MenuitemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return MainViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(this.data[position])
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}