package com.mobdeve.phexplore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemrecentLayoutBinding

class HomeMenuViewRecentAdapter(private val data: ArrayList<DestinationModel>) : RecyclerView.Adapter<HomeMenuViewRecentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewRecentHolder {
        val viewBinding: MenuitemrecentLayoutBinding = MenuitemrecentLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return HomeMenuViewRecentHolder(viewBinding)
    }


    override fun onBindViewHolder(holder: HomeMenuViewRecentHolder, position: Int) {
        holder.bindData(this.data[position])
    }
    override fun getItemCount(): Int {
        return this.data.size
    }
}