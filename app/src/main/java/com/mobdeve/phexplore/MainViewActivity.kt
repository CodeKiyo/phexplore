package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.MainmenuPageBinding

class MainViewActivity : AppCompatActivity()  {

    companion object{
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
    }

    private lateinit var mainmenuPage: MainmenuPageBinding
    private lateinit var likedLocations:ArrayList<String>
    private lateinit var likedImages:ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = MainmenuPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)
        val data = DataGenerator.loadData()
        mainmenuPage.menuRecyclerView.adapter = MainViewAdapter(data)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.mainmenuPage.username.text = intent.getStringExtra(signup_username_input).toString()


        mainmenuPage.menuRecyclerView.adapter = MainViewAdapter(DataGenerator.loadData())

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainmenuPage.menuRecyclerView.layoutManager = linearLayoutManager

        mainmenuPage.userdp.setOnClickListener{

            val intent = Intent(applicationContext, UserMenuActivity::class.java)
            intent.putExtra("LIKED_LOCATIONS", likedLocations)
            intent.putExtra("LIKED_IMAGES", likedImages)
            this.startActivity(intent)
        }
    }
}