package com.mobdeve.phexplore

import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.UserPageBinding
class HomeMenuViewActivity : AppCompatActivity()  {
    companion object{
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
    }

    private lateinit var mainmenuPage: UserPageBinding
    private lateinit var likedLocations:ArrayList<String>
    private lateinit var likedImages:ArrayList<Int>

    private val viewMenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val test = 1

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = UserPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.mainmenuPage.username.text = intent.getStringExtra(HomeMenuViewActivity.signup_username_input).toString()


        // Create LinearLayoutManager instances for each RecyclerView
        val popularLinearLayoutManager = LinearLayoutManager(this)
        val recentLinearLayoutManager = LinearLayoutManager(this)

        // Set the orientation for each LinearLayoutManager
        popularLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recentLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // Set the LayoutManagers to the corresponding RecyclerViews
        mainmenuPage.popularRecyclerview.layoutManager = popularLinearLayoutManager
        mainmenuPage.recentRecyclerview.layoutManager = recentLinearLayoutManager

        // Set the adapters for each RecyclerView
        mainmenuPage.popularRecyclerview.adapter = HomeMenuViewAdapter(DataGenerator.loadData(), viewMenuLauncher)
        mainmenuPage.recentRecyclerview.adapter = HomeMenuViewRecentAdapter(DataGenerator.loadData())
    }
}