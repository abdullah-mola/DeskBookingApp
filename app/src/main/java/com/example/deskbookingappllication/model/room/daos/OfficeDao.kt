package com.example.deskbookingappllication.model.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.deskbookingappllication.model.room.entities.Office

@Dao
interface OfficeDao {

    @Query("SELECT * FROM office_table")
    fun getAllOffices(): LiveData<List<Office>>

    @Query("SELECT * FROM office_table WHERE office_id=:id")
    fun getOfficeById(id:String):LiveData<Office>
}