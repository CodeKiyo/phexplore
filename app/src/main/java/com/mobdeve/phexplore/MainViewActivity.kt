package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.phexplore.databinding.MainmenuPageBinding

class MainViewActivity : AppCompatActivity()  {

    private lateinit var mainmenuPage: MainmenuPageBinding
    private lateinit var likedLocations:ArrayList<String>
    private lateinit var likedImages:ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainmenuPage = MainmenuPageBinding.inflate(layoutInflater)

        setContentView(mainmenuPage.root)
        val data = DataGenerator.loadData()
        mainmenuPage.menuRecyclerView.adapter = MainViewAdapter(data)

        val linearLayoutManager = LinearLayoutManager(this)

        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainmenuPage.menuRecyclerView.layoutManager = linearLayoutManager

        mainmenuPage.userdp.setOnClickListener{

            val intent = Intent(applicationContext, UserMenuActivity::class.java)
            data.forEach {
                if(it.isLiked) {
                    likedLocations.add(it.destName)
                    likedImages.add(it.destImage)
                    Toast.makeText(this, it.destName, Toast.LENGTH_SHORT).show()
                }
            }
            intent.putExtra("LIKED_LOCATIONS", likedLocations)
            intent.putExtra("LIKED_IMAGES", likedImages)
            this.startActivity(intent)
        }
    }
}