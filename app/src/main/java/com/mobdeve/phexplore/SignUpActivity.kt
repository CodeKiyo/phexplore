package com.mobdeve.phexplore

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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

        // Accessing signup_login_switch button
        val signupLoginSwitch = signupPage.signupLoginSwitch

        // Adding underline in signup_login_switch text
        val spannableString1 = SpannableString(signupLoginSwitch.text)
        spannableString1.setSpan(UnderlineSpan(), 0, signupLoginSwitch.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signupLoginSwitch.text = spannableString1

        // Accessing signup_skip textview
        val signupSkip = signupPage.signupSkip

        // Adding underline in signup_login_switch text
        val spannableString2 = SpannableString(signupSkip.text)
        spannableString2.setSpan(UnderlineSpan(), 0, signupSkip.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signupSkip.text = spannableString2

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
    }
}