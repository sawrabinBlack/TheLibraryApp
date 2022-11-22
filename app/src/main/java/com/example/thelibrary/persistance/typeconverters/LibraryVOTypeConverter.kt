package com.example.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LibraryVOTypeConverter {
    @TypeConverter
    fun toString(books: MutableList<LibraryBooksVO>?): String {
        return Gson().toJson(books)
    }

    @TypeConverter
    fun toBookVOList(bookListJsonStr: String): MutableList<LibraryBooksVO>? {
        val type = object : TypeToken<MutableList<LibraryBooksVO>?>() {}.type
        return Gson().fromJson(bookListJsonStr, type)
    }

}