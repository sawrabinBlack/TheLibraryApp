package com.example.thelibrary.persistance

import android.content.Context
import android.location.GnssAntennaInfo.Listener
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.thelibrary.data.vos.BookVO
import com.example.thelibrary.data.vos.ListVO
import com.example.thelibrary.persistance.daos.BooksDao
import com.example.thelibrary.persistance.daos.LibraryBooksDao
import com.example.thelibrary.persistance.daos.ListDao
import com.example.thelibrary.persistance.daos.ShelvesDao
import com.example.thelibrary.persistance.entities.LibraryBooksVO
import com.example.thelibrary.persistance.entities.ShelvesVO

@Database(entities = [ListVO::class,BookVO::class,ShelvesVO::class,LibraryBooksVO::class], version = 7, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {
    companion object {
        private const val DB_NAME = "THE_LIBRARY_DB"
        var dbInstance: LibraryDatabase? = null
        fun getDBInstance(context: Context): LibraryDatabase? {
            when (dbInstance) {
                null -> {
                    dbInstance = Room.databaseBuilder(context, LibraryDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return dbInstance

        }

    }

    abstract fun ListDao(): ListDao
    abstract fun booksDao():BooksDao
    abstract fun shelvesDao():ShelvesDao
    abstract  fun libraryDao():LibraryBooksDao

}

