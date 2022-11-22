package com.example.thelibrary.network.responses

import com.example.thelibrary.data.vos.ItemsVO
import com.google.gson.annotations.SerializedName

data class GetBookListFromGoogleResponse(
    @SerializedName("items")
    val items: List<ItemsVO>?
) {

}