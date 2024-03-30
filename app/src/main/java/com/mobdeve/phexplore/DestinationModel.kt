package com.mobdeve.phexplore

class DestinationModel(destName: String, destDescription: String, destImage: String, destCity: String, destCategory: String, numOfBookmarks: Int) {
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
    var numOfBookmarks = numOfBookmarks
        private set
    override fun toString(): String {
        return "DestinationModel{" +
                "destName='" + destName + '\'' +
                ", destDescription='" + destDescription +
                ", destImage=" + destImage +
                ", destCity=" + destCity +
                ", destCategory=" + destCategory +
                ", numOfBookmarks= " + numOfBookmarks +
                '}'
    }
}