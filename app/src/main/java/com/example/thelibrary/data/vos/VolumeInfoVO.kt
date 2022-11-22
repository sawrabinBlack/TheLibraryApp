package com.example.thelibrary.data.vos

import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.google.gson.annotations.SerializedName
import org.junit.experimental.categories.Category

data class VolumeInfoVO(
    @SerializedName("title")
    val title: String?,
    @SerializedName("authors")
    val authors: List<String>?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("imageLinks")
    val imageLinks: ImageLinksVO?,
    @SerializedName("categories")
    val categories: List<String>?
) {

}
