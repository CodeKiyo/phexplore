package com.mobdeve.phexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.ActivityUserPageBinding

class UserPage : AppCompatActivity() {

    private lateinit var usermenuPage : ActivityUserPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usermenuPage = ActivityUserPageBinding.inflate(layoutInflater)
        usermenuPage.favoriteRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),1)
        usermenuPage.favoriteRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        usermenuPage.favoriteRecyclerView.isNestedScrollingEnabled = false
        usermenuPage.favoriteRecyclerView.setHasFixedSize(false)
        setContentView(usermenuPage.root)
    }
}