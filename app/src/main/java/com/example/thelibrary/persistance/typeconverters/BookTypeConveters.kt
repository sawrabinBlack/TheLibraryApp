package com.example.thelibrary.persistance.typeconverters

import androidx.room.TypeConverter
import com.example.thelibrary.data.vos.BookVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookTypeConverter {

    @TypeConverter
    fun toString(books: BookVO?): String {
        return Gson().toJson(books)
    }

    @TypeConverter
    fun toBookVOList(bookListJsonStr: String): BookVO? {
        val type = object : TypeToken<BookVO?>() {}.type
        return Gson().fromJson(bookListJsonStr, type)
    }
}