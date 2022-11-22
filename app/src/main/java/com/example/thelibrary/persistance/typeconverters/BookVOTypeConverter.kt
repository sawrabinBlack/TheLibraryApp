package com.example.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.thelibrary.data.vos.BookVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookVOTypeConverter {

    @TypeConverter
    fun toString(books: List<BookVO>?): String {
        return Gson().toJson(books)
    }

    @TypeConverter
    fun toBookVOList(bookListJsonStr: String): List<BookVO>? {
        val type = object : TypeToken<List<BookVO>?>() {}.type
        return Gson().fromJson(bookListJsonStr, type)
    }
}