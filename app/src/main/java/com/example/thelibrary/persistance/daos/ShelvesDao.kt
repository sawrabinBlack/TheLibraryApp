package com.example.thelibrary.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.thelibrary.persistance.entities.ShelvesVO

@Dao
interface ShelvesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createShelf(shelf: ShelvesVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShelfList(list: List<ShelvesVO>)


    @Query("SELECT * FROM shelves")
    fun getShelves(): LiveData<List<ShelvesVO>>

    @Query("SELECT * FROM shelves Where name=:name")
    fun getShelfDetail(name: String):LiveData<ShelvesVO?>

    @Query("UPDATE shelves SET name=:updateName Where name=:oldName")
    fun renameShelfName(updateName: String, oldName: String)

    @Query("DELETE FROM shelves WHERE name=:name")
    fun deleteShelf(name: String)


}