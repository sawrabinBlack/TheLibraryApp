package com.example.thelibrary.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibrary.data.vos.ListVO

@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(lists: List<ListVO>)


    @Query("SELECT * FROM bookLists")
    fun getAllBookLists(): LiveData<List<ListVO>>

}