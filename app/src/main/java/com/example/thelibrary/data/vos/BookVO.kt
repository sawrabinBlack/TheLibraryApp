package com.example.thelibrary.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "books")
data class BookVO(
    @SerializedName("author")
    @ColumnInfo
    val author: String? = null,
    @SerializedName("book_image")
    @ColumnInfo
    val bookImage: String? = null,
    @SerializedName("book_image_width")
    @ColumnInfo
    val bookImageWidth: Int? = null,
    @SerializedName("book_image_height")
    @ColumnInfo
    val bookImageHeight: Int? = null,
    @SerializedName("book_review_link")
    @ColumnInfo
    val bookReviewLink: String? = null,
    @SerializedName("contributor")
    @ColumnInfo
    val contributor: String? = null,
    @SerializedName("created_date")
    @ColumnInfo
    val createdDate: String? = null,
    @SerializedName("contributor_note")
    @ColumnInfo
    val contributorNote: String? = null,
    @SerializedName("description")
    @ColumnInfo
    val description: String? = null,
    @SerializedName("publisher")
    @ColumnInfo
    val publisher: String? = null,
    @SerializedName("title")
    @PrimaryKey
    val title: String,


    @SerializedName("list_name")
    @ColumnInfo
    var listName: String? = null,

    @SerializedName("readStatus")
    @ColumnInfo
    var readStatus: Boolean? = false
)
