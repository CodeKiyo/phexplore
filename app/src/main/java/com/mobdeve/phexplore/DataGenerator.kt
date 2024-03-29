package com.mobdeve.phexplore

class DataGenerator {
    companion object {
        fun loadData(): ArrayList<DestinationModel> {
            val data = ArrayList<DestinationModel>()
            data.add(DestinationModel("Ayala Triangle","The Ayala " +
                    "Triangle Gardens is a 2-hectare landscaped urban park in Makati, Metro Manila, " +
                    "Philippines. It is a triangular public garden and courtyard in the center of " +
                    "the Makati Central Business District. It was named after its owner and " +
                    "developer Ayala Land, and opened to the public in November 19, 2009.",
                "https://i.imgur.com/TDenx9V.png",
                "Makati", "Test Category"))
            data.add(DestinationModel("BGC","Bonifacio Global City is a financial business district in Taguig, Metro Manila, Philippines. It is located 11 kilometers southeast of the capital city of Manila. The district experienced commercial growth following the sale of a 440 ha military base at Fort Bonifacio by the Bases Conversion and Development Authority.",
                    "https://i.imgur.com/TDenx9V.png",
                "Makati", "Test Category"))
            data.add(DestinationModel("Intramuros","Old-world Intramuros is " +
                    "home to Spanish-era landmarks like Fort Santiago, with a large stone gate and " +
                    "a shrine to national hero José Rizal. The ornate Manila Cathedral houses " +
                    "bronze carvings and stained glass windows, while the San Agustin Church " +
                    "museum has religious artwork and statues. Spanish colonial furniture and art " +
                    "fill Casa Manila museum, and horse-drawn carriages (kalesa) ply the area’s " +
                    "cobblestone streets. ",
                "https://i.imgur.com/TDenx9V.png",
                "Manila", "Test Category"))
            data.add(DestinationModel("Tinuhog ni Benny","GRILLING DELICIOUS " +
                    "AND AFFORDABLE DISHES SINCE 2006!\n",
                "https://i.imgur.com/TDenx9V.png",
                "Manila", "Test Category"))
            return data
        }
    }
}