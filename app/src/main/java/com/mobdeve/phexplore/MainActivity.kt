package com.mobdeve.phexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.phexplore.databinding.IntroPageBinding
import kotlin.random.Random
import com.mobdeve.phexplore.databinding.MainmenuPageBinding

class MainActivity : AppCompatActivity() {

    // RNG Background Image for intro_page.xml
    private val backgroundImages = arrayOf(
        R.drawable.intro_img1,
        R.drawable.intro_img2,
        R.drawable.intro_img3
    )

    private lateinit var introPage : IntroPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

<<<<<<< HEAD
        /*
        // This is to test the main menu page without the need for login
        mainPage = MainmenuPageBinding.inflate(layoutInflater)
        setContentView(mainPage.root)
        val data = DataGenerator.loadData()
        mainPage.menuRecyclerView.adapter = MainViewAdapter(data)
        val likedLocations = ArrayList<String>()
        val likedImages = ArrayList<Int>()
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mainPage.menuRecyclerView.layoutManager = linearLayoutManager
        mainPage.userdp.setOnClickListener{

            val intent = Intent(applicationContext, UserMenuActivity::class.java)
            data.forEach {
                if(it.isLiked) {
                    likedLocations.add(it.destName)
                    likedImages.add(it.destImage)
                    Toast.makeText(this, it.destName, Toast.LENGTH_SHORT).show()
                }
            }
            intent.putExtra("LIKED_LOCATIONS", likedLocations)
            intent.putExtra("LIKED_IMAGES", likedImages)

            this.startActivity(intent)
        }
         */
=======
>>>>>>> 097de54d26165d2e4a4de5e448b705b1683a990b
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

        // getStarted button taking you to signup_page.xml
        getStarted.setOnClickListener {
            val intentToLogin = Intent(this, LoginActivity::class.java)

            intentToLogin.putExtra(LoginActivity.BACKGROUND_RESOURCE_ID, backgroundImages[randomIndexBG])

            startActivity(intentToLogin)
        }


    }
}