package com.example.deskbookingappllication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate=true )
    val user_id:Int,
    val user_mail:String?,
    val user_password:Int,
    val first_name:String?,
    val last_name:String?,
    val user_department:String?
        )
