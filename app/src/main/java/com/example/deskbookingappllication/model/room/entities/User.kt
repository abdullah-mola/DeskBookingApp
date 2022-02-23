package com.example.deskbookingappllication.model.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey
    val user_id:Int,
    @ColumnInfo(name ="user_email")
    val email:String?,
    @ColumnInfo(name = "user_password")
    val password:Int,
    @ColumnInfo(name ="first_name")
    val firstname:String?,
    @ColumnInfo(name = "last_name")
    val lastname:String?,
    @ColumnInfo(name = "user_department")
    val department:String?
        )
