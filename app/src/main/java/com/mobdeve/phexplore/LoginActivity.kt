package com.mobdeve.phexplore

import android.os.Bundle
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


        // Set view for signup_page.xml
        setContentView(loginPage.root)

        // Accepting the Intent from MainActivity
        val intentFromMainActivity = intent

        // Getting the Background Drawable
        val backgroundImage = intentFromMainActivity.getIntExtra(SignUpActivity.BACKGROUND_RESOURCE_ID, 0)

        if(backgroundImage != 0){
            // loginPage.backgroundSignup.setImageResource(backgroundImage)
        }else {
            // Handle the case where no background resource ID is passed
            // You can set a default background or show an error message
            Log.e("SignUpActivity", "No background resource ID provided")
        }
    }
}