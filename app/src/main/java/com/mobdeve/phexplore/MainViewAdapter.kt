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
        holder.setLikeSwitchClickListener {
            if(this.data[position].isLiked) {
                this.data[position].isLiked = false
                holder.setLikeButtonImageResource(R.drawable.heart_svgrepo_com)
            } else {
                this.data[position].isLiked = true
                holder.setLikeButtonImageResource(R.drawable.heart_red)
            }
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}