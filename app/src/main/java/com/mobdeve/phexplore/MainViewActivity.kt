package com.mobdeve.phexplore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.MainmenuPageBinding

class MainViewActivity : AppCompatActivity()  {

    private lateinit var mainmenuPage: MainmenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = MainmenuPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)

        mainmenuPage.menuRecyclerView.adapter = MainViewAdapter(DataGenerator.loadData())

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainmenuPage.menuRecyclerView.layoutManager = linearLayoutManager
    }
}