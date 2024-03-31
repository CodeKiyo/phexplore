package com.mobdeve.phexplore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobdeve.phexplore.databinding.HomemenuPageBinding
class HomeMenuViewActivity : AppCompatActivity()  {
    private lateinit var mainmenuPage: HomemenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainmenuPage = HomemenuPageBinding.inflate(layoutInflater)
        setContentView(mainmenuPage.root)
        val username = intent.getStringExtra(IntentKeys.USERNAME.name)!!
        replaceFragment(HomeMenuPageFragment.newInstance(username))

        // The Code for Bottom Navigation
        val bottomNavigationView = mainmenuPage.BottomNavigation

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    replaceFragment(HomeMenuPageFragment.newInstance(username))
                    // finish
                    true
                }
                R.id.bottom_user -> {
                    // start activity
                    //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    replaceFragment(UserPageFragment.newInstance(username))
                    // finish
                    true
                }
                R.id.bottom_settings -> {
                    // start activity
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    if(username != "Guest") {
                        val builder = AlertDialog.Builder(this)
                        builder.setMessage("Are you sure you want to log out?")
                        builder.setCancelable(true)
                        builder.setPositiveButton("Yes") { dialog, id ->
                            val intentToLoginPage = Intent(this, LoginActivity::class.java)
                            startActivity(intentToLoginPage)
                            finish()
                        }
                        builder.setNegativeButton("No") { dialog, id -> dialog.cancel() }

                        val alert = builder.create()
                        alert.show()
                    } else {
                        val intentToLoginPage = Intent(this, LoginActivity::class.java)
                        startActivity(intentToLoginPage)
                        finish()
                    }
                    // finish
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }
}