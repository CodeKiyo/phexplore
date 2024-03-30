package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.phexplore.databinding.UserPageFragmentBinding

class UserPageFragment : Fragment(R.layout.user_page_fragment)  {
    companion object{
        const val TAG = "UserPageFragment"
        private const val ARG_USERNAME = "username"

        fun newInstance(username: String): UserPageFragment {
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            return UserPageFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var favoriteRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_page_fragment, container, false)

        // Replace username TextView with current logged in username
        val username = arguments?.getString(ARG_USERNAME)
        val usernameTextView = view.findViewById<TextView>(R.id.username)
        usernameTextView.text = username

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView)
        favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        // Display the destinations the user has bookmarked
        // Firebase database and data initialization
        val db = Firebase.firestore
        val usersRef = db.collection(MyFirestoreReferences.USERS_COLLECTION)
        //var userBookmarkNames = List()
        val userBookmarksDestinations = ArrayList<DestinationModel>()
        val destName = MyFirestoreReferences.DESTNAME_FIELD
        val destDescription = MyFirestoreReferences.DESTDESCRIPTION_FIELD
        val destCity = MyFirestoreReferences.DESTCITY_FIELD
        val destImage = MyFirestoreReferences.DESTIMAGE_FIELD
        val destCategory = MyFirestoreReferences.DESTCATEGORY_FIELD
        val bookmarks = MyFirestoreReferences.BOOKMARKS_FIELD

        // retrieve all documents in the destinations collection to filter
        //val data = HashMap<String, Any>()
        val docRef = db.collection("myCollection").document("myDocument")

        var userBookmarkNames = ArrayList<String>()
        var userBookmarks = ArrayList<DestinationModel>()

        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
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
                            destinationsRef
                                .whereEqualTo(MyFirestoreReferences.DESTNAME_FIELD, element)
                                .get()
                                .addOnSuccessListener { documents ->
                                    for (document in documents) {
                                        val newData = DestinationModel(
                                            document.get(destName).toString(),
                                            document.get(destDescription).toString(),
                                            document.get(destImage).toString(),
                                            document.get(destCity).toString(),
                                            document.get(destCategory).toString())
                                        userBookmarks.add(newData)
                                    }
                                    favoriteRecyclerView.adapter = DestinationAdapter(userBookmarks, 2)
                                    return@addOnSuccessListener
                                }
                        }
                    } else {
                        println("Document does not contain dataArray field or it's not an ArrayList")
                    }
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }

        /*
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)

        destinationsRef.get().addOnSuccessListener { result ->
            for (document in result!!.documents) {
                val condition = document.get(destName).toString()
                for(item in userBookmarkNames) {
                    if(item == condition) {
                        val newData = DestinationModel(
                            document.get(destName).toString(),
                            document.get(destDescription).toString(),
                            document.get(destImage).toString(),
                            document.get(destCity).toString(),
                            document.get(destCategory).toString())
                        userBookmarksDestinations.add(newData)
                    }
                }
            }
            favoriteRecyclerView.adapter = DestinationAdapter(userBookmarksDestinations, 1)
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

         */

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView)
        favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        return view
    }

    private fun getBookmarkedArray(username: String, array: ArrayList<String>) {

    }
}