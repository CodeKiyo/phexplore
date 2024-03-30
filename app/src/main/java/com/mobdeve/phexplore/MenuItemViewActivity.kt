package com.mobdeve.phexplore

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import com.facebook.share.model.ShareLinkContent
import com.facebook.share.widget.ShareDialog
import com.mobdeve.phexplore.databinding.MenuitemviewPageBinding
import com.squareup.picasso.Picasso


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
        val clientToken = getString(R.string.facebook_client_token)
        FacebookSdk.setClientToken(clientToken)
        FacebookSdk.fullyInitialize()
        setContentView(menuitemPage.root)
        // Replaces the username with what the user inputs from the Sign Up Page
        this.menuitemPage.destName.text = intent.getStringExtra(dest_name).toString()
        this.menuitemPage.destDescription.text = intent.getStringExtra(dest_description).toString()
        val imageURL = intent.getStringExtra(dest_image).toString()
        Picasso.get().load(imageURL).into(this.menuitemPage.destImage)
        this.menuitemPage.destCity.text = intent.getStringExtra(dest_city).toString()

        menuitemPage.ShareCard.setOnClickListener {

            // Share to Facebook
            /*
            val image = BitmapFactory.decodeResource(resources, R.drawable.intro_img1)

            val photo: SharePhoto = SharePhoto.Builder()
                .setBitmap(image)
                .build()

            val content: SharePhotoContent = SharePhotoContent.Builder()
                .addPhoto(photo)
                .build()
            */
            val content: ShareLinkContent = ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(imageURL))
                .setQuote("Check out "+menuitemPage.destName.text.toString()+" in "+menuitemPage.destCity.text.toString()+"!")
                .build()
            ShareDialog.show(this@MenuItemViewActivity, content)
            /*
            Picasso.get()
                .load(imageURL)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        bitmap?.let { loadedBitmap ->
                            // Share to Facebook
                            val photo: SharePhoto = SharePhoto.Builder()
                                .setBitmap(bitmap)
                                .build()
                            val content: SharePhotoContent = SharePhotoContent.Builder()
                                .addPhoto(photo)
                                .build()
                            ShareDialog.show(this@MenuItemViewActivity, content)
                        }
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        // Handle the case when loading the image fails
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        // Handle any preparations before loading the image
                    }
                })
             */

        }
    }



}