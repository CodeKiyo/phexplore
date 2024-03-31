package com.mobdeve.phexplore

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class DestinationViewHolder(itemView:View, private val username: String): RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.destName)
    private val image: ImageView = itemView.findViewById(R.id.destImage)
    private val location: TextView = itemView.findViewById(R.id.destLocation)
    private val description: TextView? = itemView.findViewById(R.id.destDescription)
    private val category: TextView? = itemView.findViewById(R.id.destCategory)
    private val bookmarkButton: CardView? = itemView.findViewById(R.id.verticalBookmarkStateCard)
    private val bookmarkIcon: ImageView? = itemView.findViewById(R.id.BookmarkState)
    private val bookmarkAmount: TextView? = itemView.findViewById(R.id.bookmarkAmount)
    fun bindData(model: DestinationModel) {
        this.name.text = model.destName
        Picasso.get().load(model.destImage).into(this.image)
        this.location.text = model.destCity
        this.description?.text = model.destDescription
        this.category?.text = model.destCategory
        this.bookmarkAmount?.text = model.numOfBookmarks.toString()


        val db = Firebase.firestore
        val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val bookmarks = MyFirestoreReferences.BOOKMARKS_FIELD
        var userBookmarkNames = ArrayList<String>()
        var clicked = false

        usersRef
            .whereEqualTo(MyFirestoreReferences.USERNAME_FIELD, username)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val data = document.get(bookmarks) as? ArrayList<String>
                    if (data != null) {
                        // Add all elements of data to dataArray
                        userBookmarkNames.addAll(data)
                        for (element in userBookmarkNames) {
                            if(element == this.name.text) {
                                this.bookmarkIcon?.setImageResource(R.drawable.homemenu_bookmark_true)
                                clicked = true
                                return@addOnSuccessListener
                            }
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }

        this.bookmarkButton?.setOnClickListener{
            if(isValidUser(username)) {
                if(clicked == false) {
                    clicked = true
                    this.bookmarkIcon?.setImageResource(R.drawable.homemenu_bookmark_true)
                    usersRef
                        .whereEqualTo(MyFirestoreReferences.USERNAME_FIELD, username)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.update(
                                    MyFirestoreReferences.BOOKMARKS_FIELD,
                                    FieldValue.arrayUnion(this.name.text)
                                )
                                destinationsRef
                                    .whereEqualTo(MyFirestoreReferences.DESTNAME_FIELD, this.name.text)
                                    .get()
                                    .addOnSuccessListener { documents ->
                                        for (document in documents) {
                                            document.reference.update(
                                                MyFirestoreReferences.BOOKMARKAMOUNT_FIELD,
                                                document.get(MyFirestoreReferences.BOOKMARKAMOUNT_FIELD).toString().toInt() + 1
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
                    this.bookmarkIcon?.setImageResource(R.drawable.homemenu_bookmark_false)
                    usersRef
                        .whereEqualTo(MyFirestoreReferences.USERNAME_FIELD, username)
                        .get()
                        .addOnSuccessListener { documents ->
                            for (document in documents) {
                                document.reference.update(
                                    MyFirestoreReferences.BOOKMARKS_FIELD,
                                    FieldValue.arrayRemove(this.name.text)
                                )
                                destinationsRef
                                    .whereEqualTo(MyFirestoreReferences.DESTNAME_FIELD, this.name.text)
                                    .get()
                                    .addOnSuccessListener { documents ->
                                        for (document in documents) {
                                            document.reference.update(
                                                MyFirestoreReferences.BOOKMARKAMOUNT_FIELD,
                                                document.get(MyFirestoreReferences.BOOKMARKAMOUNT_FIELD).toString().toInt() - 1
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
                val builder = AlertDialog.Builder(itemView.context)
                builder.setMessage("You need to be logged in to bookmark.")
                builder.setCancelable(true)
                val alert = builder.create()
                alert.show()
            }
        }

        this.itemView.setOnClickListener {
            val intentToViewItem = Intent(itemView.context, MenuItemViewActivity::class.java)
            intentToViewItem.putExtra("DEST_NAME", model.destName)
            intentToViewItem.putExtra("DEST_IMAGE", model.destImage)
            intentToViewItem.putExtra("DEST_CITY", model.destCity)
            intentToViewItem.putExtra("DEST_DESCRIPTION", model.destDescription)
            if(clicked == true) {
                intentToViewItem.putExtra("isBookmarked", true)
            } else {
                intentToViewItem.putExtra("isBookmarked", false)
            }
            intentToViewItem.putExtra("username", username)
            (itemView.context as? Activity)?.startActivity(intentToViewItem, null)
        }
    }

    private fun isValidUser(username: String): Boolean {
        if(username != "Guest")
            return true
        else
            return false
    }
}