package com.mobdeve.phexplore

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.MenuitemLayoutBinding

class HomeMenuViewAdapter(private val data: ArrayList<DestinationModel>) : RecyclerView.Adapter<HomeMenuViewHolder>() {

    companion object{
        const val dest_name : String = "DEST_NAME"
        const val dest_description : String = "DEST_DESCRIPTION"
        const val dest_image : String = "DEST_IMAGE"

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeMenuViewHolder {
        val viewBinding: MenuitemLayoutBinding = MenuitemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return HomeMenuViewHolder(viewBinding)
    }


    override fun onBindViewHolder(holder: HomeMenuViewHolder, position: Int) {
        holder.bindData(this.data[position])
        holder.itemView.setOnClickListener {
            var intentToViewItem = Intent(holder.itemView.context, MenuItemViewActivity::class.java)
            intentToViewItem.putExtra(dest_name, this.data[(position)].destName)
            intentToViewItem.putExtra(dest_description, this.data[(position)].destDescription)
            intentToViewItem.putExtra(dest_image, this.data[(position)].destImage)
            startActivity(holder.itemView.context, intentToViewItem, null)
        }
    }
    override fun getItemCount(): Int {
        return this.data.size
    }
}