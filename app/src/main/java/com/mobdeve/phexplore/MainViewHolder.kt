package com.mobdeve.phexplore

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class MainViewHolder(private val viewBinding: MenuitemLayoutBinding): RecyclerView.ViewHolder(viewBinding.root) {
    fun bindData(model: DestinationModel) {
        this.viewBinding.destName.text = model.destName
        this.viewBinding.destImage.setImageResource(model.destImage)
        if(model.isLiked) {
            this.viewBinding.likeButton.setImageResource(R.drawable.heart_red)
        } else {
            this.viewBinding.likeButton.setImageResource(R.drawable.heart_svgrepo_com)
        }
    }
    fun setLikeSwitchClickListener(onClickListener: View.OnClickListener){
        this.viewBinding.likeButton.setOnClickListener(onClickListener)
    }
    fun setLikeButtonImageResource(resourceId: Int) {
        this.viewBinding.likeButton.setImageResource(resourceId)
    }
}