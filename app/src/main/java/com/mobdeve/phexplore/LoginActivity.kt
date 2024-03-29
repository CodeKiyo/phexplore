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

        // Getting the Login Button and going to the Main Menu
        this.loginPage.loginButton.setOnClickListener(View.OnClickListener {
            val username = this.loginPage.loginUsernameInput.text.toString()
            val password = this.loginPage.loginPasswordInput.text.toString()

            validateAccount(username, password)
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

    // Checks if username and password exist in the database
    private fun validateAccount(username: String, password: String) {
        val db = Firebase.firestore
        val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)

        val usernameQuery = usersRef.whereEqualTo(
            MyFirestoreReferences.USERNAME_FIELD,
            username
        )

        val passwordQuery = usersRef.whereEqualTo(
            MyFirestoreReferences.PASSWORD_FIELD,
            password
        )

        usernameQuery.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // If there are no results, inform the user to create an account.
                if (task.result.isEmpty) {
                    showError(1)
                } else { // Otherwise, validate account
                    passwordQuery.get().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // If there are no results, inform the user to enter the correct password
                            if (task.result.isEmpty) {
                                showError(2)
                            } else { // Otherwise, login
                                loginSuccess(username)
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.exception)
                        }
                    }
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.exception)
            }
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }
    }

    private fun showError(errorKey: Int) {
        if (errorKey == 1) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("User not found. Create an account to continue.")
            builder.setCancelable(true)
            val alert = builder.create()
            alert.show()
        } else if (errorKey == 2) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Wrong password. Please type your correct password.")
            builder.setCancelable(true)
            val alert = builder.create()
            alert.show()
        }
    }

    private fun loginSuccess(username: String) {
        val intentToMainMenu = Intent(this, HomeMenuViewActivity::class.java)
        intentToMainMenu.putExtra(IntentKeys.USERNAME.name, username)
        intentToMainMenu.putExtra(signup_username_input, username)
        startActivity(intentToMainMenu)
        finish()
    }
}