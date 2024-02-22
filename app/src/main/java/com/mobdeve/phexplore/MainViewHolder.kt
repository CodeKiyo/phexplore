package com.mobdeve.phexplore

import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class MainViewHolder(private val viewBinding: MenuitemLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    fun bindData(model: DestinationModel) {
        this.viewBinding.destName.text = model.destName
        this.viewBinding.destImage.setImageResource(model.destImage)
    }
}