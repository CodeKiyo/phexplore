package com.mobdeve.phexplore

import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemrecentLayoutBinding

class HomeMenuViewRecentHolder(private val viewBinding: MenuitemrecentLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    fun bindData(model: DestinationModel) {
        this.viewBinding.recentDestname.text = model.destName
        this.viewBinding.recentDestimg.setImageResource(model.destImage)
    }
}