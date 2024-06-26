package com.mobdeve.phexplore

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.mobdeve.phexplore.databinding.IntroPageBinding
import kotlin.random.Random
import com.mobdeve.phexplore.databinding.HomemenuPageBinding
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    // RNG Background Image for intro_page.xml
    private val backgroundImages = arrayOf(
        R.drawable.intro_img1,
        R.drawable.intro_img2,
        R.drawable.intro_img3
    )

    private lateinit var introPage : IntroPageBinding
    private val SPFILE = "SharedPref"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //check if logged in
        loadFromSharedPref()
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
    private fun loadFromSharedPref() {
        val sp = this.getSharedPreferences(SPFILE, Context.MODE_PRIVATE)
        val userName = sp.getString(LoginActivity.signup_username_input, "NONE")
        val userInput = sp.getString(IntentKeys.USERNAME.name, "NONE")
        if (!(userName == "NONE" || userInput == "NONE")){
            val intentToMainMenu = Intent(this, HomeMenuViewActivity::class.java)
            intentToMainMenu.putExtra(IntentKeys.USERNAME.name, userName)
            startActivity(intentToMainMenu)
            finish()
        }
    }
}