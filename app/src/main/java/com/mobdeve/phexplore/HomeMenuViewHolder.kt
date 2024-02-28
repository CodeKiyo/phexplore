package com.mobdeve.phexplore

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class HomeMenuViewHolder(private val viewBinding: MenuitemLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    fun bindData(model: DestinationModel) {
        this.viewBinding.destName.text = model.destName
        this.viewBinding.destImage.setImageResource(model.destImage)
    }
}