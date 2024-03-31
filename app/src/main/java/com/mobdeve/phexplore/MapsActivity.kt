package com.mobdeve.phexplore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mobdeve.phexplore.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    companion object {
        const val dest_name = "dest_name"
    }

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val db = Firebase.firestore
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val destName = intent.getStringExtra(dest_name)

        destinationsRef.whereEqualTo(MyFirestoreReferences.DESTNAME_FIELD, destName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    // Retrieve the document ID
                    // Add a marker in Sydney and move the camera
                    val latitude = document.get(MyFirestoreReferences.LATITUDE_FIELD).toString().toDouble()
                    val longitude = document.get(MyFirestoreReferences.LONGITUDE_FIELD).toString().toDouble()
                    val location = LatLng(latitude, longitude)
                    mMap.addMarker(MarkerOptions().position(location).title("Marker in location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13F))
                }
            }.addOnFailureListener { exception ->
                // Handle any errors
                println("Error getting documents: $exception")
            }


    }
}