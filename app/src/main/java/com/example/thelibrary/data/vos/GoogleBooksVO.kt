package com.example.thelibrary.data.vos

import com.google.gson.annotations.SerializedName

data class GoogleBooksVO(
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfoVO?
)
