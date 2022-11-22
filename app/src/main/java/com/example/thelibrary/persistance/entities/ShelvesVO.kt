package com.example.thelibrary.persistance.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.persistance.typeconverters.BookVOTypeConverter
import com.example.thelibrary.persistance.typeconverters.LibraryVOTypeConverter

@Entity(tableName = "shelves")
@TypeConverters(
    LibraryVOTypeConverter::class
)
data class ShelvesVO(
    @PrimaryKey
    var name:String,
    @ColumnInfo(name = "books")
    var books:MutableList<LibraryBooksVO>?
)
