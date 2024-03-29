package com.mobdeve.phexplore

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.mobdeve.phexplore.databinding.HomemenuPageBinding
class HomeMenuViewActivity : AppCompatActivity()  {
    companion object{
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
    }

    private lateinit var mainmenuPage: HomemenuPageBinding
    private lateinit var likedLocations:ArrayList<String>
    private lateinit var likedImages:ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = HomemenuPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.mainmenuPage.username.text = intent.getStringExtra(signup_username_input).toString()

        // Set the orientation for each LinearLayoutManager

        // Set the LayoutManagers to the corresponding RecyclerViews
        mainmenuPage.horizontalRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mainmenuPage.verticalRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mainmenuPage.verticalRecyclerView.isNestedScrollingEnabled = false
        mainmenuPage.verticalRecyclerView.setHasFixedSize(false)

        // Set the adapters for each RecyclerView
        mainmenuPage.horizontalRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),0)
        mainmenuPage.verticalRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),1)


        //attach clicklistener to user picture
        mainmenuPage.userDp.setOnClickListener {
            val intent = Intent(this, UserPage::class.java)
            startActivity(intent)
        }

        // The Code for Bottom Navigation
        val bottomNavigationView = mainmenuPage.BottomNavigation

        bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // finish
                    true
                }
                R.id.bottom_user -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // finish
                    true
                }
                R.id.bottom_settings -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    // finish
                    true
                }
                else -> false
            }
        }




    }
}