package com.example.deskbookingappllication.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "office_table")
data class Office(
    @PrimaryKey(autoGenerate = true)
    val office_id:Int,
    val office_name:String,
    val map:String
)