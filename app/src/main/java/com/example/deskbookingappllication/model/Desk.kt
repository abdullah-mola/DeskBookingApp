package com.example.deskbookingappllication.model

import com.google.gson.annotations.SerializedName

data class Desk(
    @SerializedName("id")
    val desk_id: Int,
    val label: String,
    val office: Office,
    val equipment: List<String>,
    val map: String,
    val fixDeskUserId: String
)