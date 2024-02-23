package com.mobdeve.phexplore

class DataGenerator {
    companion object {
        fun loadData(): ArrayList<DestinationModel> {
            val data = ArrayList<DestinationModel>()
            data.add(DestinationModel("Taft", R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("Tondo", R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("EGI Tower", R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("DLSU", R.drawable.mainmenu_destimage_img))
            return data
        }
    }
}