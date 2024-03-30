package com.mobdeve.phexplore

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.phexplore.databinding.SignupPageBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SignUpActivity : AppCompatActivity() {
    companion object{
        const val BACKGROUND_RESOURCE_ID = "BACKGROUND_RESOURCE_ID"
        const val TAG = "SignUpActivity"
    }

    private lateinit var signupPage: SignupPageBinding
    private val db = Firebase.firestore
    private val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)

    // Variables to determine the state of each input as valid before signing up
    // Should be used as conditions in signing up and moving to the MainMenuView only if are set to true
    private var usernameState = false
    private var emailState = false
    private var passwordState = false
    private var birthdayState = false

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

        // Adding underline in signup_skip text
        val spannableString2 = SpannableString(signupSkip.text)
        spannableString2.setSpan(UnderlineSpan(), 0, signupSkip.text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        signupSkip.text = spannableString2

        // Set view for signup_page.xml
        setContentView(signupPage.root)

        // Accepting the Intent from MainActivity
        val intentFromLoginActivity = intent
        val backgroundImage = intentFromLoginActivity.getIntExtra(BACKGROUND_RESOURCE_ID, 0)

        if(backgroundImage != 0){
            signupPage.backgroundSignup.setImageResource(backgroundImage)
        }

        // Error and Validation Mode in username input layout
        val usernameInputLayout = signupPage.signupUsernameLayout
        val usernameInput = signupPage.signupUsernameInput

        usernameInput.doOnTextChanged{ text, start, before, count ->

            val textCompare = Regex("[^A-Za-z0-9]")

            if (textCompare.containsMatchIn(text.toString())){
                usernameInputLayout.error = "Must use A-Z and 0-9 only."
                usernameInputLayout.isErrorEnabled = true

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Error)
                val colorStateList = ColorStateList.valueOf(colorState)

                usernameInputLayout.setStartIconTintList(colorStateList)

                usernameState = false
            }
            else if (text?.length !in 6..18 && text!!.isNotEmpty()){
                usernameInputLayout.error = "Must be 6-18 characters only."
                usernameInputLayout.isErrorEnabled = true

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Error)
                val colorStateList = ColorStateList.valueOf(colorState)

                usernameInputLayout.setStartIconTintList(colorStateList)

                usernameState = false
            }
            else if (text!!.isBlank()){
                usernameInputLayout.error = null
                usernameInputLayout.isErrorEnabled = false
                usernameInputLayout.helperText = "Required*"

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Required)
                val colorStateList = ColorStateList.valueOf(colorState)

                usernameInputLayout.setHelperTextColor(colorStateList)
                usernameInputLayout.isEndIconVisible = false

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)
                usernameInputLayout.setStartIconTintList(colorStateListIcon)

                usernameState = false
            }
            else{
                usernameInputLayout.error = null
                usernameInputLayout.isErrorEnabled = true
                usernameInputLayout.helperText = "Valid Username."

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Valid)
                val colorStateList = ColorStateList.valueOf(colorState)

                usernameInputLayout.setHelperTextColor(colorStateList)

                val checkDrawable = ContextCompat.getDrawable(this@SignUpActivity, R.drawable.signup_check_icon)

                usernameInputLayout.endIconDrawable = checkDrawable
                usernameInputLayout.setEndIconTintList(colorStateList)
                usernameInputLayout.isEndIconVisible = true

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)

                usernameInputLayout.setStartIconTintList(colorStateListIcon)

                usernameState = true
            }
        }

        // Error and Validation Mode in username input layout
        val emailInputLayout = signupPage.signupEmailLayout
        val emailInput = signupPage.signupEmailInput

        emailInput.doOnTextChanged{ text, start, before, count ->

            if (!text.isNullOrEmpty() && !Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                emailInputLayout.error = "Invalid Email."
                emailInputLayout.isErrorEnabled = true

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Error)
                val colorStateList = ColorStateList.valueOf(colorState)

                emailInputLayout.setStartIconTintList(colorStateList)

                emailState = false
            }
            else if (text!!.isBlank()){
                emailInputLayout.error = null
                emailInputLayout.isErrorEnabled = false
                emailInputLayout.helperText = "Required*"

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Required)
                val colorStateList = ColorStateList.valueOf(colorState)

                emailInputLayout.setHelperTextColor(colorStateList)
                emailInputLayout.isEndIconVisible = false

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)

                emailInputLayout.setStartIconTintList(colorStateListIcon)

                emailState = false
            }
            else{
                emailInputLayout.error = null
                emailInputLayout.isErrorEnabled = false
                emailInputLayout.helperText = "Valid Email."

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Valid)
                val colorStateList = ColorStateList.valueOf(colorState)

                emailInputLayout.setHelperTextColor(colorStateList)

                val checkDrawable = ContextCompat.getDrawable(this@SignUpActivity, R.drawable.signup_check_icon)

                emailInputLayout.endIconDrawable = checkDrawable
                emailInputLayout.setEndIconTintList(colorStateList)
                emailInputLayout.isEndIconVisible = true

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)

                emailInputLayout.setStartIconTintList(colorStateListIcon)

                emailState = true
            }
        }

        // Error and Validation Mode in password input layout
        val passwordInputLayout = signupPage.signupPasswordLayout
        val passwordInput = signupPage.signupPasswordInput

        passwordInput.doOnTextChanged{ text, start, before, count ->

            if(text!!.contains(" ")){
                passwordInputLayout.error = "Spaces aren't allowed."
                passwordInputLayout.isErrorEnabled = true

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Error)
                val colorStateList = ColorStateList.valueOf(colorState)

                passwordInputLayout.setStartIconTintList(colorStateList)

                passwordState = false
            }
            else if (text.length !in 8..12 && text.isNotEmpty()) {
                passwordInputLayout.error = "Must be 8-12 characters only."
                passwordInputLayout.isErrorEnabled = true

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Error)
                val colorStateList = ColorStateList.valueOf(colorState)

                passwordInputLayout.setStartIconTintList(colorStateList)

                passwordState = false
            }
            else if (text.isBlank()){
                passwordInputLayout.error = null
                passwordInputLayout.isErrorEnabled = false
                passwordInputLayout.helperText = "Required*"

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Required)
                val colorStateList = ColorStateList.valueOf(colorState)

                passwordInputLayout.setHelperTextColor(colorStateList)
                passwordInputLayout.isEndIconVisible = false

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)

                passwordInputLayout.setStartIconTintList(colorStateListIcon)

                passwordState = false
            }
            else{
                passwordInputLayout.error = null
                passwordInputLayout.isErrorEnabled = false
                passwordInputLayout.helperText = "Valid Password."

                val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Valid)
                val colorStateList = ColorStateList.valueOf(colorState)

                passwordInputLayout.setHelperTextColor(colorStateList)

                val checkDrawable = ContextCompat.getDrawable(this@SignUpActivity, R.drawable.signup_check_icon)

                passwordInputLayout.endIconDrawable = checkDrawable
                passwordInputLayout.setEndIconTintList(colorStateList)
                passwordInputLayout.isEndIconVisible = true

                val colorStateIcon = ContextCompat.getColor(this@SignUpActivity, R.color.Default)
                val colorStateListIcon = ColorStateList.valueOf(colorStateIcon)

                passwordInputLayout.setStartIconTintList(colorStateListIcon)

                passwordState = true
            }
        }

        // Setting the Birthdate Picker Logic and the Error and Validation Process
        val birthdayLayout = signupPage.signupBirthdayLayout
        val birthdayDisplay = signupPage.signupBirthdayDisplay
        val calendarButton = signupPage.signupBirthdayButtonInput

        calendarButton.setOnClickListener{
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date of Birth")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setTheme(R.style.Theme_Material3DatePicker)
                .build()

            materialDatePicker.addOnPositiveButtonClickListener { selection ->
                if (selection != null) {
                    val date =
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(selection))
                    val spannableStringDate = SpannableStringBuilder(date)
                    birthdayDisplay.text = spannableStringDate

                    birthdayLayout.error = null
                    birthdayLayout.isErrorEnabled = false
                    birthdayLayout.helperText = "Valid Date."

                    val colorState = ContextCompat.getColor(this@SignUpActivity, R.color.Valid)
                    val colorStateList = ColorStateList.valueOf(colorState)

                    birthdayLayout.setHelperTextColor(colorStateList)

                    val checkDrawable = ContextCompat.getDrawable(this@SignUpActivity, R.drawable.signup_check_icon)

                    calendarButton.icon = checkDrawable
                    calendarButton.iconTint = colorStateList

                    birthdayState = true
                }
            }

            materialDatePicker.show(supportFragmentManager, "tag")
        }

        // Getting the Sign Up Button and going to the Main Menu
        val signupButton = signupPage.signupButton

        signupButton.setOnClickListener{
            if(usernameState && emailState && passwordState && birthdayState) {
                val username = this.signupPage.signupUsernameInput.text.toString()
                val password = this.signupPage.signupPasswordInput.text.toString()
                val email = this.signupPage.signupEmailInput.text.toString()
                val birthdate = this.signupPage.signupBirthdayDisplay.text.toString()

                validateRegister(username, email, birthdate, password)
            }
        }

        // Switching to the Login page
        signupLoginSwitch.setOnClickListener{

            val intentToLogin = Intent(this, LoginActivity::class.java)

            intentToLogin.putExtra(LoginActivity.BACKGROUND_RESOURCE_ID, backgroundImage)

            startActivity(intentToLogin)
            finish()
        }

        // Going to the Main Menu but skipping credentials
        signupSkip.setOnClickListener{

            val intentToMainMenuViaSkip = Intent(this, HomeMenuViewActivity::class.java)

            startActivity(intentToMainMenuViaSkip)
            finish()
        }
    }

    private fun validateRegister(username: String, email: String, birthdate: String, password: String) {
        validateEmail(email) { isValid ->
            if (isValid) {
                // Email is valid
                validateUsername(username) { isValid ->
                    if (isValid) {
                        // Username is valid
                        if(validateBirth(birthdate)) {
                            val data: MutableMap<String, Any> = HashMap()
                            data[MyFirestoreReferences.USERNAME_FIELD] = username
                            data[MyFirestoreReferences.PASSWORD_FIELD] = password
                            data[MyFirestoreReferences.EMAIL_FIELD] = email
                            data[MyFirestoreReferences.BIRTHDATE_FIELD] = birthdate
                            data[MyFirestoreReferences.BOOKMARKS_FIELD] = ArrayList<String>()

                            usersRef
                                .add(data)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.id)
                                    val i = Intent(this@SignUpActivity, HomeMenuViewActivity::class.java)
                                    i.putExtra(IntentKeys.USERNAME.name, username)
                                    startActivity(i)
                                    finish()
                                }
                                .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
                        } else {
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage("Must be 18 or above.")
                            builder.setCancelable(true)
                            val alert = builder.create()
                            alert.show()
                        }
                    } else {
                        // Username is not valid
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("Username already exists.")
                        builder.setCancelable(true)
                        val alert = builder.create()
                        alert.show()
                    }
                }
            } else {
                // Email is not valid
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Email already exists.")
                builder.setCancelable(true)
                val alert = builder.create()
                alert.show()
            }
        }
    }

    private fun validateEmail(email: String, onComplete: (Boolean) -> Unit) {
        val emailQuery = usersRef.whereEqualTo(
            MyFirestoreReferences.EMAIL_FIELD,
            email
        )

        emailQuery.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // If there are no results, email is valid
                if (task.result.isEmpty) {
                    onComplete(true)
                } else { // Otherwise, email is not valid
                    onComplete(false)
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.exception)
                onComplete(false)
            }
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
            onComplete(false)
        }
    }

    private fun validateUsername(username: String, onComplete: (Boolean) -> Unit) {
        val usernameQuery = usersRef.whereEqualTo(
            MyFirestoreReferences.EMAIL_FIELD,
            username
        )

        usernameQuery.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // If there are no results, username is valid
                if (task.result.isEmpty) {
                    onComplete(true)
                } else { // Otherwise, username is not valid
                    onComplete(false)
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.exception)
                onComplete(false)
            }
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
            onComplete(false)
        }
    }

    private fun validateBirth(birthdate: String): Boolean {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val birthYear = birthdate.split("-")
        return currentYear - birthYear[0].toInt() >= 18
    }
}