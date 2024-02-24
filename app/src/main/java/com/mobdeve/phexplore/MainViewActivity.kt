package com.mobdeve.phexplore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.MainmenuPageBinding

class MainViewActivity : AppCompatActivity()  {

    companion object{
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
    }

    private lateinit var mainmenuPage: MainmenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = MainmenuPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.mainmenuPage.username.text = intent.getStringExtra(signup_username_input).toString()


        mainmenuPage.menuRecyclerView.adapter = MainViewAdapter(DataGenerator.loadData())

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainmenuPage.menuRecyclerView.layoutManager = linearLayoutManager
    }
}