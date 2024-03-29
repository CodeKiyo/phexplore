package com.mobdeve.phexplore


import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FilterViewHolder(itemView: View, private val verticalRecyclerView: RecyclerView): RecyclerView.ViewHolder(itemView) {
    private val filter_name: TextView = itemView.findViewById(R.id.filter_name)
    private val filter_image: ImageView = itemView.findViewById(R.id.filter_icon)
    private val filter_cardcolor: CardView = itemView.findViewById(R.id.filterCardView)

    fun bindData(model: FilterModel) {
        this.filter_name.text = model.filterName
        this.filter_image.setImageResource(model.filterImage)
        this.filter_cardcolor.backgroundTintList = ColorStateList.valueOf(Color.parseColor(model.filterColor))

        this.itemView.setOnClickListener {
            val db = Firebase.firestore
            val destinationsRef = db.collection(MyFirestoreReferences.DESTINATIONS_COLLECTION)
            val data = ArrayList<DestinationModel>()
            val destName = MyFirestoreReferences.DESTNAME_FIELD
            val destDescription = MyFirestoreReferences.DESTDESCRIPTION_FIELD
            val destCity = MyFirestoreReferences.DESTCITY_FIELD
            val destImage = MyFirestoreReferences.DESTIMAGE_FIELD
            val destCategory = MyFirestoreReferences.DESTCATEGORY_FIELD
            destinationsRef.get().addOnSuccessListener { result ->
                for (document in result!!.documents) {
                    val condition = document.get(destCategory).toString()
                    if(condition == this.filter_name.text) {
                        val newData = DestinationModel(
                            document.get(destName).toString(),
                            document.get(destDescription).toString(),
                            document.get(destImage).toString(),
                            document.get(destCity).toString(),
                            document.get(destCategory).toString())
                        data.add(newData)
                    }
                }
                verticalRecyclerView.adapter = DestinationAdapter(data, 1)
            }.addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
        }
    }
}