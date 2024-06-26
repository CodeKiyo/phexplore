package com.mobdeve.phexplore

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        val usernameTextView = view.findViewById<TextView>(R.id.username)

        usernameTextView.text = username

        // Set up recyclerview layouts
        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        verticalRecyclerView.isNestedScrollingEnabled = false
        verticalRecyclerView.setHasFixedSize(false)

        // transition to userpage fragment
        view.findViewById<ImageView>(R.id.user_dp).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.BottomNavigation)
            bottomNav?.selectedItemId = R.id.bottom_user
        }

        // The Code for Filter Recycler View
        filterRecyclerView = view.findViewById(R.id.filterRecyclerView)
        filterRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        filterRecyclerView.adapter = FilterAdapter(FilterGenerator.loadData(),verticalRecyclerView, username.toString())

        updateView()

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        updateView()
    }

    private fun updateView() {
        val destName = MyFirestoreReferences.DESTNAME_FIELD
        val destDescription = MyFirestoreReferences.DESTDESCRIPTION_FIELD
        val destCity = MyFirestoreReferences.DESTCITY_FIELD
        val destImage = MyFirestoreReferences.DESTIMAGE_FIELD
        val destCategory = MyFirestoreReferences.DESTCATEGORY_FIELD
        val bookmarkAmount = MyFirestoreReferences.BOOKMARKAMOUNT_FIELD

        // Update verticalRecyclerView
        val db = Firebase.firestore
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val data = ArrayList<DestinationModel>()

        destinationsRef.get().addOnSuccessListener { result ->
            for (document in result!!.documents) {
                val newData = DestinationModel(
                    document.get(destName).toString(),
                    document.get(destDescription).toString(),
                    document.get(destImage).toString(),
                    document.get(destCity).toString(),
                    document.get(destCategory).toString(),
                    document.get(bookmarkAmount).toString().toInt(),
                    "",
                    "")
                data.add(newData)
            }
            verticalRecyclerView.adapter = DestinationAdapter(data, 1, arguments?.getString(ARG_USERNAME).toString())
            verticalRecyclerView.adapter?.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }

        // Update horizontalRecyclerView
        val recommendedData = ArrayList<DestinationModel>()

        destinationsRef.get().addOnSuccessListener { result ->
            for (document in result!!.documents) {
                if (document.get(bookmarkAmount).toString().toInt() >= 4) {
                    val newData = DestinationModel(
                        document.get(destName).toString(),
                        document.get(destDescription).toString(),
                        document.get(destImage).toString(),
                        document.get(destCity).toString(),
                        document.get(destCategory).toString(),
                        document.get(bookmarkAmount).toString().toInt(),
                        "",
                        "")
                    recommendedData.add(newData)
                }
            }
            horizontalRecyclerView.adapter = DestinationAdapter(recommendedData, 0, arguments?.getString(ARG_USERNAME).toString())
            horizontalRecyclerView.adapter?.notifyDataSetChanged()
        }.addOnFailureListener { exception ->
            println("Error getting documents: $exception")
        }
    }

}