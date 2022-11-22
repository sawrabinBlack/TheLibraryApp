package com.example.thelibrary.persistance.daos

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.persistance.entities.LibraryBooksVO

@Dao
interface LibraryBooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(book: LibraryBooksVO)


    @Query("SELECT * FROM libraryBooks")
    fun getBooksFromLibrary(): LiveData<List<LibraryBooksVO>>

    @Query("SELECT * FROM libraryBooks WHERE title=:title")
    fun getLibraryBookOnShot(title: String):LiveData<LibraryBooksVO?>

    @Query("DELETE  FROM libraryBooks WHERE title=:title")
    fun deleteBooksFromLibrary(title: String)
}