package com.mobdeve.phexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
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
    private lateinit var mainPage : MainmenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This is to test the main menu page without the need for login

        mainPage = MainmenuPageBinding.inflate(layoutInflater)
        setContentView(mainPage.root)
        mainPage.menuRecyclerView.adapter = MainViewAdapter(DataGenerator.loadData())
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mainPage.menuRecyclerView.layoutManager = linearLayoutManager



        /*
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
        val getStarted = introPage.getStarted

        // Adding underline in getStarted text
        val spannableString = SpannableString(getStarted.text)
        spannableString.setSpan(UnderlineSpan(), 0, getStarted.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        introPage.getStarted.text = spannableString

        // Set view for intro_page.xml
        setContentView(introPage.root)

        // getStarted button taking you to signup_page.xml
        getStarted.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)

            intent.putExtra(SignUpActivity.BACKGROUND_RESOURCE_ID, backgroundImages[randomIndexBG])

            startActivity(intent)
        }

         */
    }
}