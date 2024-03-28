package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.mobdeve.phexplore.databinding.LoginPageBinding
import com.google.firebase.firestore.ktx.firestore

class LoginActivity : AppCompatActivity() {
    // redirect ko yung main activity to login activity instead of signup activity soon
    companion object{
        const val BACKGROUND_RESOURCE_ID = "BACKGROUND_RESOURCE_ID"
        private const val TAG = "LoginActivity"
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
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

        this.loginPage.loginButton.setOnClickListener(View.OnClickListener {
            val username = this.loginPage.loginUsernameInput.text.toString()
            //val password = this.loginPage.loginPasswordInput.text.toString()

            val db = Firebase.firestore
            val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)

            val usernameQuery = usersRef.whereEqualTo(
                MyFirestoreReferences.USERNAME_FIELD,
                username
            )

            /*
            val passwordQuery = usersRef.whereEqualTo(
                MyFirestoreReferences.PASSWORD_FIELD,
                password
            )
             */

            usernameQuery.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // If there are no results, inform the user to create an account.
                    if (task.result.isEmpty) {
                        val builder = AlertDialog.Builder(this)

                        builder.setMessage("User not found. Create an account to continue")
                        builder.setCancelable(true)

                        val alert = builder.create()
                        alert.show()
                    } else { // Otherwise, login

                        val intentToMainMenu = Intent(this, HomeMenuViewActivity::class.java)
                        intentToMainMenu.putExtra(IntentKeys.USERNAME.name, username)
                        intentToMainMenu.putExtra(signup_username_input, username)
                        startActivity(intentToMainMenu)
                        finish()
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
        })

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