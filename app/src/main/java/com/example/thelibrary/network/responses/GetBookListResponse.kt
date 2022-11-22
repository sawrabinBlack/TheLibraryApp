package com.example.thelibrary.network.responses

import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.data.vos.ResultVO
import com.google.gson.annotations.SerializedName

data class GetBookListResponse(
    @SerializedName("status")
    val status: String?,
    @SerializedName("results")
    val results: ResultVO?
) {
}