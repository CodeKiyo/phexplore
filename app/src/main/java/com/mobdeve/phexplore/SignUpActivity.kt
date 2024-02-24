package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.mobdeve.phexplore.databinding.SignupPageBinding

class SignUpActivity : AppCompatActivity() {
    companion object{
        const val BACKGROUND_RESOURCE_ID = "BACKGROUND_RESOURCE_ID"
    }

    private lateinit var signupPage: SignupPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding
        signupPage = SignupPageBinding.inflate(layoutInflater)

        // Set view for signup_page.xml
        setContentView(signupPage.root)

        // Accepting the Intent from MainActivity
        val intentFromMainActivity = intent

        // Getting the Background Drawable
        val backgroundImage = intentFromMainActivity.getIntExtra(BACKGROUND_RESOURCE_ID, 0)

        if(backgroundImage != 0){
            signupPage.backgroundSignup.setImageResource(backgroundImage)
        }else {
            // Handle the case where no background resource ID is passed
            // You can set a default background or show an error message
            Log.e("SignUpActivity", "No background resource ID provided")
        }

        val signupButton = this.signupPage.signupCreateaccount
        signupButton.setOnClickListener {
            val testIntent = Intent(this, MainViewActivity::class.java)
            testIntent.putExtra(MainViewActivity.signup_username_input, signupPage.signupUsernameInput.text.toString())
            startActivity(testIntent)
        }


    }
}