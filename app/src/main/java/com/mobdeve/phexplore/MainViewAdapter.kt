package com.mobdeve.phexplore

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class MainViewAdapter(private val data: ArrayList<DestinationModel>, private val test: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<MainViewHolder>() {

    companion object{
        const val dest_name : String = "DEST_NAME"
        const val dest_description : String = "DEST_DESCRIPTION"
        const val dest_image : Int = 1

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val viewBinding: MenuitemLayoutBinding = MenuitemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return MainViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(this.data[position])
<<<<<<< Updated upstream
        holder.setLikeSwitchClickListener {
            if(this.data[position].isLiked) {
                this.data[position].isLiked = false
                holder.setLikeButtonImageResource(R.drawable.heart_svgrepo_com)
            } else {
                this.data[position].isLiked = true
                holder.setLikeButtonImageResource(R.drawable.heart_red)
            }
=======

        holder.itemView.setOnClickListener {
            var intentToViewItem = Intent(holder.itemView.context, MenuItemViewActivity::class.java)
            intentToViewItem.putExtra(dest_name, this.data[(position)].destName)
            intentToViewItem.putExtra(dest_description, this.data[(position)].destDescription)
            intentToViewItem.putExtra(dest_image.toString(), this.data[(position)].destImage)
            test.launch(intentToViewItem)
>>>>>>> Stashed changes
        }
    }

    override fun getItemCount(): Int {
        return this.data.size
    }
}