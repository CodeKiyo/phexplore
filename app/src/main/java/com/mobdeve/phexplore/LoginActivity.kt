package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.phexplore.databinding.LoginPageBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    // redirect ko yung main activity to login activity instead of signup activity soon
    companion object{
        const val BACKGROUND_RESOURCE_ID = "BACKGROUND_RESOURCE_ID"
    }

    private lateinit var loginPage: LoginPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        loginPage = LoginPageBinding.inflate(layoutInflater)

        // Accessing login_signup_switch textview
        val loginSignupSwitch = loginPage.loginSignupSwitch

        // Adding underline in login_signup-switch text
        val spannableString1 = SpannableString(loginSignupSwitch.text)
        spannableString1.setSpan(UnderlineSpan(), 0, loginSignupSwitch.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        loginSignupSwitch.text = spannableString1

        // Accessing login_skip textview
        val loginSkip = loginPage.loginSkip

        // Adding underline in login_skip text
        val spannableString2 = SpannableString(loginSkip.text)
        spannableString2.setSpan(UnderlineSpan(), 0, loginSkip.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        loginSkip.text = spannableString2

        // Set view for signup_page.xml
        setContentView(loginPage.root)

        // Accepting the Intent from MainActivity
        val intentFromMainActivity = intent

        // Getting the Background Drawable
        val backgroundImage = intentFromMainActivity.getIntExtra(BACKGROUND_RESOURCE_ID, 0)

        // Setting the Background Drawable
        if(backgroundImage != 0){
            loginPage.backgroundLogin.setImageResource(backgroundImage)
        }

        // Getting the Login Button and going to the Main Menu
        val loginButton = loginPage.loginButton

        loginButton.setOnClickListener{

            finish()

            val intentToMainMenu = Intent(this, MainViewActivity::class.java)

            startActivity(intentToMainMenu)
        }

        // Switching to the Sign Up page
        loginSignupSwitch.setOnClickListener{

            finish()

            val intentToSignUp = Intent(this, SignUpActivity::class.java)

            intentToSignUp.putExtra(SignUpActivity.BACKGROUND_RESOURCE_ID, backgroundImage)

            startActivity(intentToSignUp)
        }

        // Going to the Main Menu but skipping credentials
        loginSkip.setOnClickListener{

            finish()

            val intentToMainMenuViaSkip = Intent(this, MainViewActivity::class.java)

            startActivity(intentToMainMenuViaSkip)
        }
    }
}