package com.mobdeve.phexplore

class DataGenerator {
    companion object {
        fun loadData(): ArrayList<DestinationModel> {
            val data = ArrayList<DestinationModel>()
            data.add(DestinationModel("Taft", R.drawable.taft))
            data.add(DestinationModel("Tondo", R.drawable.taft))
            data.add(DestinationModel("EGI Tower", R.drawable.taft))
            data.add(DestinationModel("DLSU", R.drawable.taft))
            return data
        }
    }
}