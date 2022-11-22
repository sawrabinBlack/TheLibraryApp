package com.example.thelibrary.data.vos

import com.google.gson.annotations.SerializedName

data class ImageLinksVO(
    @SerializedName("smallThumbnail")
    val smallThumbnail:String?,
    @SerializedName("thumbnail")
    val thumbnail:String?
) {
    fun httpsConnectionFaker(): String {
        return thumbnail?.substring(0, 4) + "s" + thumbnail?.substring(4)
    }
}