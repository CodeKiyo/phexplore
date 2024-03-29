package com.mobdeve.phexplore

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.io.ByteArrayOutputStream

class HomeMenuPageFragment : Fragment(R.layout.homemenu_fragment) {
    companion object{
        const val TAG = "HomeMenuFragment"
        private const val ARG_USERNAME = "username"

        fun newInstance(username: String): HomeMenuPageFragment {
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            return HomeMenuPageFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var filterRecyclerView: RecyclerView

    @SuppressLint("WrongThread")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.homemenu_fragment, container, false)

        val username = arguments?.getString(ARG_USERNAME)

        var usernameTextView = view.findViewById<TextView>(R.id.username)

        usernameTextView.text = username

        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        verticalRecyclerView.isNestedScrollingEnabled = false
        verticalRecyclerView.setHasFixedSize(false)

        //Testing database destination list
        val db = Firebase.firestore
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val data = ArrayList<DestinationModel>()
        val destName = MyFirestoreReferences.DESTNAME_FIELD
        val destDescription = MyFirestoreReferences.DESTDESCRIPTION_FIELD
        val destCity = MyFirestoreReferences.DESTCITY_FIELD
        val destImage = MyFirestoreReferences.DESTIMAGE_FIELD
        val destCategory = MyFirestoreReferences.DESTCATEGORY_FIELD

        // retrieve all documents in the destinations collection
        destinationsRef.get().addOnSuccessListener { result ->
            for (document in result!!.documents) {
                val newData = DestinationModel(
                    document.get(destName).toString(),
                    document.get(destDescription).toString(),
                    document.get(destImage).toString(),
                    document.get(destCity).toString(),
                    document.get(destCategory).toString())
                data.add(newData)
                Log.d(TAG, DataGenerator.loadData().size.toString())
            }
            horizontalRecyclerView.adapter = DestinationAdapter(data, 0)
            verticalRecyclerView.adapter = DestinationAdapter(data, 1)
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

        view.findViewById<ImageView>(R.id.userDp).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.BottomNavigation)
            bottomNav?.selectedItemId = R.id.bottom_user
        }

        // The Code for Filter Recycler View
        filterRecyclerView = view.findViewById(R.id.filterRecyclerView)
        filterRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        filterRecyclerView.adapter = FilterAdapter(FilterGenerator.loadData(),verticalRecyclerView)

        /*
        this.mainmenuPage.username.text = intent.getStringExtra(signup_username_input).toString()
         */

        return view
    }
}