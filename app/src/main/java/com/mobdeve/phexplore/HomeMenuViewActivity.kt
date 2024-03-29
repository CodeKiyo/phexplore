package com.mobdeve.phexplore

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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
        replaceFragment(HomeMenuPageFragment())

        // The Code for Filter Recycler View
        mainmenuPage.filterRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        mainmenuPage.filterRecyclerView.adapter = FilterAdapter(FilterGenerator.loadData())

        // The Code for Bottom Navigation
        val bottomNavigationView = mainmenuPage.BottomNavigation

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    replaceFragment(HomeMenuPageFragment())
                    // finish
                    true
                }
                R.id.bottom_user -> {
                    // start activity
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    replaceFragment(UserPageFragment())
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

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}