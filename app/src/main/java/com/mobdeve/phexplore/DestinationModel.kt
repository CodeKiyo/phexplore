package com.mobdeve.phexplore

class DestinationModel(destName: String, destImage: Int) {
    var destName = destName
        private set
    var destImage = destImage
        private set
    var isLiked = false
    override fun toString(): String {
        return "DestinationModel{" +
                "destName='" + destName + '\'' +
                ", destImage=" + destImage +
                '}'
    }
}