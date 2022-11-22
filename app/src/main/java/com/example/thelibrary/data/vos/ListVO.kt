package com.example.thelibrary.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.thelibrary.persistance.typeconverters.BookTypeConverter
import com.example.thelibrary.persistance.typeconverters.BookVOTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bookLists")
@TypeConverters(
    BookVOTypeConverter::class,
    BookTypeConverter::class
)
data class ListVO(

    @SerializedName("list_id")
    @PrimaryKey
    val listId: Int?,
    @SerializedName("list_name")
    @ColumnInfo(name = "list_name")
    val listName: String?,
    @SerializedName("list_name_encoded")
    @ColumnInfo(name = "list_name_encoded")
    val listNameEncoded: String?,
    @SerializedName("display_name")
    @ColumnInfo(name="display_name")
    val displayName: String?,
    @SerializedName("books")
    @ColumnInfo(name = "books")
    val books: List<BookVO>?,

    @SerializedName("book_details")
    val bookDetail:List<BookVO>?,



)




