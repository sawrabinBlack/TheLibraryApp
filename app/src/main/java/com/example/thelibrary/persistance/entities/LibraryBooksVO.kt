package com.example.thelibrary.persistance.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "libraryBooks")
data class LibraryBooksVO(
    @SerializedName("author")
    @ColumnInfo
    val author: String?,
    @SerializedName("book_image")
    @ColumnInfo
    val bookImage: String?,
    @SerializedName("book_image_width")
    @ColumnInfo
    val bookImageWidth: Int?=null,
    @SerializedName("book_image_height")
    @ColumnInfo
    val bookImageHeight: Int?=null,
    @SerializedName("book_review_link")
    @ColumnInfo
    val bookReviewLink: String?=null,
    @SerializedName("contributor")
    @ColumnInfo
    val contributor: String?=null,
    @SerializedName("created_date")
    @ColumnInfo
    val createdDate: String?=null,
    @SerializedName("contributor_note")
    @ColumnInfo
    val contributorNote: String?=null,
    @SerializedName("description")
    @ColumnInfo
    val description: String?=null,
    @SerializedName("publisher")
    @ColumnInfo
    val publisher: String?=null,
    @SerializedName("title")
    @PrimaryKey
    val title: String,


    @SerializedName("list_name")
    @ColumnInfo
    var listName:String?
)
