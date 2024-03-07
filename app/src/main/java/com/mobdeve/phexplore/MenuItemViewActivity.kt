package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.phexplore.databinding.MenuitemviewPageBinding

class MenuItemViewActivity : AppCompatActivity()  {

    companion object{
        const val dest_name : String = "DEST_NAME"
        const val dest_description : String = "DEST_DESCRIPTION"
        const val dest_image : String = "DEST_IMAGE"
        const val dest_city : String = "DEST_CITY"

    }

    private lateinit var menuitemPage: MenuitemviewPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        menuitemPage = MenuitemviewPageBinding.inflate(layoutInflater)

        setContentView(menuitemPage.root)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.menuitemPage.destName.text = intent.getStringExtra(dest_name).toString()
        this.menuitemPage.destDescription.text = intent.getStringExtra(dest_description).toString()
        this.menuitemPage.destImage.setImageResource(intent.getIntExtra(dest_image, 0))
        this.menuitemPage.destCity.text = intent.getStringExtra(dest_city).toString()
    }
}