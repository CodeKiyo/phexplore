package com.mobdeve.phexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.ActivityUserMenuBinding

class UserMenuActivity : AppCompatActivity() {
    private lateinit var userMenuBinding: ActivityUserMenuBinding
    private val likedLocations = ArrayList<DestinationModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userMenuBinding = ActivityUserMenuBinding.inflate(layoutInflater)
        setContentView(userMenuBinding.root)
        val getLikedLocations = intent.getStringArrayListExtra("LIKED_LOCATIONS")
        val getLikedImages = intent.getIntegerArrayListExtra("LIKED_IMAGES")
        Log.d("ReceivedData", "Liked Locations: ${getLikedLocations?.joinToString()}")
        Log.d("ReceivedData", "Liked Images: ${getLikedImages?.joinToString()}")
        if (getLikedLocations != null && getLikedImages != null) {
            for(i in 0 until getLikedLocations.size) {
                likedLocations.add(DestinationModel(getLikedLocations[i], getLikedImages[i]))
                likedLocations[i].isLiked = true
            }
        }
        val data = DataGenerator.loadData()
        userMenuBinding.menuRecyclerView.adapter = MainViewAdapter(likedLocations)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        userMenuBinding.menuRecyclerView.layoutManager = linearLayoutManager


    }
}