package com.mobdeve.phexplore

class DestinationModel(destName: String, destDescription: String, destImage: Int, destCity: String, destCategory: String) {
    var destName = destName
        private set
    var destImage = destImage
        private set
    var isLiked = false
    var destDescription = destDescription
        private set
    var destCity = destCity
        private set
    var destCategory = destCategory
        private set
    override fun toString(): String {
        return "DestinationModel{" +
                "destName='" + destName + '\'' +
                ", destDescription='" + destDescription +
                ", destImage=" + destImage +
                ", destCity=" + destCity +
                ", destCategory=" + destCategory +
                '}'
    }
}