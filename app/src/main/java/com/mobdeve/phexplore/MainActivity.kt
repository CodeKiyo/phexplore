package com.mobdeve.phexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.phexplore.databinding.IntroPageBinding
import kotlin.random.Random
import com.mobdeve.phexplore.databinding.HomemenuPageBinding
import com.mobdeve.phexplore.databinding.UserPageBinding

class MainActivity : AppCompatActivity() {
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

    /*
    // RNG Background Image for intro_page.xml
    private val backgroundImages = arrayOf(
        R.drawable.intro_img1,
        R.drawable.intro_img2,
        R.drawable.intro_img3
    )

    private lateinit var introPage : IntroPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        introPage = IntroPageBinding.inflate(layoutInflater)

        // RNG Texts for intro_page.xml
        val quotes = arrayOf(
            getString(R.string.quote1),
            getString(R.string.quote2),
            getString(R.string.quote3)
        )

        // RNG Logic
        val randomIndexBG = Random.nextInt(backgroundImages.size)
        introPage.backgroundIntro.setImageResource(backgroundImages[randomIndexBG])
        val randomIndexQ = Random.nextInt(quotes.size)
        introPage.quote.text = quotes[randomIndexQ]

        // Accessing getStarted button
        val getStarted = introPage.introGetstarted

        // Adding underline in getStarted text
        val spannableString = SpannableString(getStarted.text)
        spannableString.setSpan(UnderlineSpan(), 0, getStarted.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        introPage.introGetstarted.text = spannableString

        // Set view for intro_page.xml
        setContentView(introPage.root)

        // getStarted button taking you to Login Page
        getStarted.setOnClickListener {
            val intentToLogin = Intent(this, LoginActivity::class.java)

            intentToLogin.putExtra(LoginActivity.BACKGROUND_RESOURCE_ID, backgroundImages[randomIndexBG])

            startActivity(intentToLogin)
            finish()
        }
    }

     */
}