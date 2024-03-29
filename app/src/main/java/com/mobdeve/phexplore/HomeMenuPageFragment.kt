package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeMenuPageFragment : Fragment(R.layout.homemenu_fragment) {
    companion object{
        const val signup_username_input : String = "SIGNUP_USERNAME_INPUT"
    }

    private lateinit var horizontalRecyclerView: RecyclerView
    private lateinit var verticalRecyclerView: RecyclerView
    private lateinit var filterRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.homemenu_fragment, container, false)

        horizontalRecyclerView = view.findViewById(R.id.horizontalRecyclerView)
        horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        verticalRecyclerView = view.findViewById(R.id.verticalRecyclerView)
        verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        verticalRecyclerView.isNestedScrollingEnabled = false
        verticalRecyclerView.setHasFixedSize(false)

        horizontalRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(), 0)
        verticalRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(), 1)

        view.findViewById<ImageView>(R.id.userDp).setOnClickListener {
            val bottomNav = activity?.findViewById<BottomNavigationView>(R.id.BottomNavigation)
            bottomNav?.selectedItemId = R.id.bottom_user
        }

        // The Code for Filter Recycler View
        filterRecyclerView = view.findViewById(R.id.filterRecyclerView)
        filterRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        filterRecyclerView.adapter = FilterAdapter(FilterGenerator.loadData())

        /*
        this.mainmenuPage.username.text = intent.getStringExtra(signup_username_input).toString()
         */

        return view
    }
}