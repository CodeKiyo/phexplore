package com.mobdeve.phexplore

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.phexplore.databinding.MenuitemviewPageBinding
import com.squareup.picasso.Picasso


class MenuItemViewActivity : AppCompatActivity()  {

    companion object{
        const val dest_name : String = "DEST_NAME"
        const val dest_description : String = "DEST_DESCRIPTION"
        const val dest_image : String = "DEST_IMAGE"
        const val dest_city : String = "DEST_CITY"
        const val isBookmarked : String = "isBookmarked"
        const val username : String = "username"

    }

    private lateinit var menuitemPage: MenuitemviewPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        menuitemPage = MenuitemviewPageBinding.inflate(layoutInflater)
        val clientToken = getString(R.string.facebook_client_token)
        FacebookSdk.setClientToken(clientToken)
        FacebookSdk.fullyInitialize()
        setContentView(menuitemPage.root)
        // Replaces the username with what the user inputs from the Sign Up Page
        this.menuitemPage.destName.text = intent.getStringExtra(dest_name).toString()
        this.menuitemPage.destDescription.text = intent.getStringExtra(dest_description).toString()
        val imageURL = intent.getStringExtra(dest_image).toString()
        val condition = intent.getBooleanExtra(isBookmarked, false)
        if(condition == true) {
            this.menuitemPage.BookmarkIcon.setImageResource(R.drawable.details_bookmark_true)
        } else {
            this.menuitemPage.BookmarkIcon.setImageResource(R.drawable.details_bookmark_false)
        }

        var clicked = condition

        println(condition)
        Picasso.get().load(imageURL).into(this.menuitemPage.destImage)
        this.menuitemPage.destCity.text = intent.getStringExtra(dest_city).toString()
        menuitemPage.LocationCard.setOnClickListener {
            val intent = intent
            intent.setClass(this, MapsActivity::class.java)
            intent.putExtra("dest_name", this.menuitemPage.destName.text)
            startActivity(intent)
        }

        val db = Firebase.firestore
        val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val bookmarks = MyFirestoreReferences.BOOKMARKS_FIELD
        var userBookmarkNames = ArrayList<String>()
        val usernameText = intent.getStringExtra(username)

        menuitemPage.BookmarkCard.setOnClickListener {
            if(usernameText != "Guest") {
                if (clicked == false) {
                    clicked = true
                    this.menuitemPage.BookmarkIcon.setImageResource(R.drawable.details_bookmark_true)
                    usersRef
                        .whereEqualTo(MyFirestoreReferences.USERNAME_FIELD, usernameText)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.update(
                                    MyFirestoreReferences.BOOKMARKS_FIELD,
                                    FieldValue.arrayUnion(this.menuitemPage.destName.text)
                                )
                                destinationsRef
                                    .whereEqualTo(
                                        MyFirestoreReferences.DESTNAME_FIELD,
                                        this.menuitemPage.destName.text
                                    )
                                    .get()
                                    .addOnSuccessListener { documents ->
                                        for (document in documents) {
                                            document.reference.update(
                                                MyFirestoreReferences.BOOKMARKAMOUNT_FIELD,
                                                document.get(MyFirestoreReferences.BOOKMARKAMOUNT_FIELD)
                                                    .toString().toInt() + 1
                                            )
                                        }
                                    }
                            }
                        }
                        .addOnFailureListener { exception ->
                            println("Error getting documents: $exception")
                        }
                } else {
                    clicked = false
                    this.menuitemPage.BookmarkIcon.setImageResource(R.drawable.details_bookmark_false)
                    usersRef
                        .whereEqualTo(MyFirestoreReferences.USERNAME_FIELD, usernameText)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.update(
                                    MyFirestoreReferences.BOOKMARKS_FIELD,
                                    FieldValue.arrayRemove(this.menuitemPage.destName.text)
                                )
                                destinationsRef
                                    .whereEqualTo(
                                        MyFirestoreReferences.DESTNAME_FIELD,
                                        this.menuitemPage.destName.text
                                    )
                                    .get()
                                    .addOnSuccessListener { documents ->
                                        for (document in documents) {
                                            document.reference.update(
                                                MyFirestoreReferences.BOOKMARKAMOUNT_FIELD,
                                                document.get(MyFirestoreReferences.BOOKMARKAMOUNT_FIELD)
                                                    .toString().toInt() - 1
                                            )
                                        }
                                    }
                            }
                        }
                        .addOnFailureListener { exception ->
                            println("Error getting documents: $exception")
                        }
                }
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("You need to be logged in to bookmark.")
                builder.setCancelable(true)
                val alert = builder.create()
                alert.show()
            }

        }
        menuitemPage.ShareCard.setOnClickListener {
            val content: ShareLinkContent = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(imageURL))
                .setQuote("Check out " + menuitemPage.destName.text.toString() + " in " + menuitemPage.destCity.text.toString() + "!")
                .build()
            ShareDialog.show(this@MenuItemViewActivity, content)
        }
    }


}