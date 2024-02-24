package com.mobdeve.phexplore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobdeve.phexplore.databinding.MenuitemviewPageBinding

class MenuItemViewActivity : AppCompatActivity()  {

    companion object{
        const val dest_name : String = "DEST_NAME"
        const val dest_description : String = "DEST_DESCRIPTION"
        const val dest_image : Int = 1

    }

    private lateinit var menuitemPage: MenuitemviewPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        menuitemPage = MenuitemviewPageBinding.inflate(layoutInflater)

        setContentView(menuitemPage.root)

        // Replaces the username with what the user inputs from the Sign Up Page
        this.menuitemPage.destName.text = intent.getStringExtra(dest_name).toString()
        this.menuitemPage.destDescription.text = intent.getStringExtra(dest_description).toString()
        this.menuitemPage.destImage.setImageResource(R.drawable.mainmenu_destimage_img)
    }
}