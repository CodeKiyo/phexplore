package com.mobdeve.phexplore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.phexplore.databinding.UserPageFragmentBinding

class UserPageFragment : Fragment(R.layout.user_page_fragment)  {
    private lateinit var favoriteRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_page_fragment, container, false)

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView)
        favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        favoriteRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),2)

        return view
    }
}