package com.example.deskbookingappllication.model.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "office_table")
data class Office(
    @PrimaryKey
    @SerializedName("id")
    val office_id: Int,
    @SerializedName("name")
    val office_name: String,
    val map: String
)