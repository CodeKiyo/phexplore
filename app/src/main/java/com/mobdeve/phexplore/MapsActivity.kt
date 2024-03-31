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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val db = Firebase.firestore
        val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
        val destName = intent.getStringExtra(dest_name)

        destinationsRef.whereEqualTo(MyFirestoreReferences.DESTNAME_FIELD, destName)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val latitude = document.get(MyFirestoreReferences.LATITUDE_FIELD).toString().toDouble()
                    val longitude = document.get(MyFirestoreReferences.LONGITUDE_FIELD).toString().toDouble()
                    val location = LatLng(latitude, longitude)
                    mMap.addMarker(MarkerOptions().position(location).title("Marker in location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13F))
                }
            }.addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }


    }
}