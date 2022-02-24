package com.example.deskbookingappllication.model

import com.google.gson.annotations.SerializedName


data class Office(
    @SerializedName("id")
    val office_id: Int,
    @SerializedName("name")
    val office_name: String,
    val map: String
)