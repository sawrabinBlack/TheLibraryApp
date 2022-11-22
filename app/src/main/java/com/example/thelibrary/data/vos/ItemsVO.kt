package com.example.thelibrary.data.vos

import com.google.gson.annotations.SerializedName

data class ItemsVO(
    @SerializedName("volumeInfo")
    val volumeInfo: VolumeInfoVO?


) {
    fun transformToLibraryVO(): BookVO {
        return BookVO(
            author = volumeInfo?.authors?.firstOrNull(),
            bookImage = volumeInfo?.imageLinks?.thumbnail,
            title = volumeInfo?.title ?: "",
            listName = volumeInfo?.categories?.firstOrNull(),
            createdDate = volumeInfo?.publishedDate,
            description = volumeInfo?.description,
            publisher = volumeInfo?.publishedDate
        )
    }
}

