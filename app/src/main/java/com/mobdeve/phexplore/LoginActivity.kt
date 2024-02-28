package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.phexplore.databinding.LoginPageBinding

class LoginActivity : AppCompatActivity() {
    // redirect ko yung main activity to login activity instead of signup activity soon
    companion object{
        const val BACKGROUND_RESOURCE_ID = "BACKGROUND_RESOURCE_ID"
    }

    private lateinit var loginPage: LoginPageBinding

    // Variables to determine the state of each input as valid before logging in
    // Should be used as conditions in logging in and moving to the MainMenuView only if are set to true
    private var usernameState = false
    private var passwordState = false

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
        val backgroundImage = intentFromMainActivity.getIntExtra(BACKGROUND_RESOURCE_ID, 0)

        // Setting the Background Drawable
        if(backgroundImage != 0){
            loginPage.backgroundLogin.setImageResource(backgroundImage)
        }

        // Error and Validation Mode in username and password for Login
        // The logic should be implemented inside the loginButton
        val usernameInputLayout = loginPage.loginUsernameLayout
        val usernameInput = loginPage.loginUsernameInput
        val passwordInputLayout = loginPage.loginPasswordLayout
        val passwordInput = loginPage.loginPasswordInput


        // Getting the Login Button and going to the Main Menu
        val loginButton = loginPage.loginButton

        loginButton.setOnClickListener{

            // This is where the checking of existing or registered username and password should be implemented
            // And it should wrap the next if statement below inside {}

                    // Should have an if statement about the state variables
                    // similar to the logic implemented in SignUpActivity.kt signup button

                    val intentToMainMenu = Intent(this, HomeMenuViewActivity::class.java)

                    startActivity(intentToMainMenu)
                    finish()
        }

        // Switching to the Sign Up page
        loginSignupSwitch.setOnClickListener{

            val intentToSignUp = Intent(this, SignUpActivity::class.java)

            intentToSignUp.putExtra(SignUpActivity.BACKGROUND_RESOURCE_ID, backgroundImage)

            startActivity(intentToSignUp)
            finish()
        }

        // Going to the Main Menu but skipping credentials
        loginSkip.setOnClickListener{

            val intentToMainMenuViaSkip = Intent(this, HomeMenuViewActivity::class.java)

            startActivity(intentToMainMenuViaSkip)
            finish()
        }
    }
}