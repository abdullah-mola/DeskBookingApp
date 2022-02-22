package com.example.deskbookingappllication.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "desk_table")
data class Desk(
    @PrimaryKey(autoGenerate = true)
    val desk_id:Int,
    val lable:String,
    val office:Office,
    val equipment: List<String>,
    val map: String,
    val fixDeskUserId: String
)