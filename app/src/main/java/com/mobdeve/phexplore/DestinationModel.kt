package com.mobdeve.phexplore

class DestinationModel(destName: String, destDescription: String, destImage: Int) {
    var destName = destName
        private set
    var destImage = destImage
        private set
<<<<<<< Updated upstream
    var isLiked = false
=======
    var destDescription = destDescription
        private set

>>>>>>> Stashed changes
    override fun toString(): String {
        return "DestinationModel{" +
                "destName='" + destName + '\'' +
                ", destDescription='" + destDescription +
                ", destImage=" + destImage +
                '}'
    }
}