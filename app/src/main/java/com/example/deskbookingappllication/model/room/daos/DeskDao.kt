package com.example.deskbookingappllication.model.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.deskbookingappllication.model.room.entities.Desk

@Dao
interface DeskDao {

    @Query("SELECT * FROM desk_table")
    fun getAllDesks(): LiveData<List<Desk>>

}