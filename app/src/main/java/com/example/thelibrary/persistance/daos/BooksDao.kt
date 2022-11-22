package com.example.thelibrary.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibrary.data.vos.BookVO

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(list: List<BookVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOneBook(book: BookVO)

    @Query("SELECT * FROM books WHERE readStatus= :status")
    fun getLibrary(status: Boolean): LiveData<List<BookVO>>

    @Query("UPDATE books SET readStatus= :status WHERE title=:title")
    fun addOrRemoveFromLibrary(status: Boolean, title: String)

    @Query("SELECT * FROM books WHERE title= :title")
    fun getBooksByOneShot(title: String): BookVO?
}