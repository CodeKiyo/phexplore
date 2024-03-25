package com.mobdeve.phexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.mobdeve.phexplore.databinding.UserPageBinding

class UserPage : AppCompatActivity() {

    private lateinit var usermenuPage : UserPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usermenuPage = UserPageBinding.inflate(layoutInflater)

        setContentView(usermenuPage.root)

        usermenuPage.favoriteRecyclerView.layoutManager = GridLayoutManager(this, 2)
        // Set the adapters for each RecyclerView
        usermenuPage.favoriteRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),2)
    }
}