package com.mobdeve.phexplore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        val username = arguments?.getString(ARG_USERNAME)

        val usernameTextView = view.findViewById<TextView>(R.id.username)

        usernameTextView.text = username

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView)
        favoriteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        favoriteRecyclerView.adapter = DestinationAdapter(DataGenerator.loadData(),2)

        return view
    }
}