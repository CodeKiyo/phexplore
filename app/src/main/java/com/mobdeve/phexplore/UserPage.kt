package com.mobdeve.phexplore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobdeve.phexplore.databinding.ActivityUserPageBinding

class UserPage : AppCompatActivity() {

    private lateinit var usermenuPage : ActivityUserPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usermenuPage = ActivityUserPageBinding.inflate(layoutInflater)

        setContentView(usermenuPage.root)
    }
}