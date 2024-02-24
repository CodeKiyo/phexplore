package com.mobdeve.phexplore

class DataGenerator {
    companion object {
        fun loadData(): ArrayList<DestinationModel> {
            val data = ArrayList<DestinationModel>()
            data.add(DestinationModel("Taft","is a major road in the south of Metro Manila. It passes through three cities in the metropolis: Manila, Pasay, and Parañaque. The road was named after the former Governor-General of the Philippines and U.S. President, William Howard Taft; the Philippines was a former commonwealth territory of the United States in the first half of the 20th century. The avenue is a component of National Route 170 (N170), a secondary road in the Philippine highway network, and anchors R-2 of the Manila arterial road network.\n",
                R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("Tondo","Tondo is a district located in Manila, Philippines. It is the largest, in terms of area and population, of Manila's sixteen districts, with a census-estimated 654,220 people in 2020. It consists of two congressional districts. It is also the second most densely populated district in the city.",
                R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("EGI Tower","EGI Taft Tower is a Condominium in Manila, one of the top cities in Metro Manila",
                R.drawable.mainmenu_destimage_img))
            data.add(DestinationModel("DLSU","also referred to as DLSU, De La Salle or La Salle, is a private, Catholic coeducational research university run by the Institute of the Brothers of the Christian Schools in Taft Avenue, Malate, Manila, Philippines.",
                R.drawable.mainmenu_destimage_img))
            return data
        }
    }
}