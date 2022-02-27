package com.example.deskbookingappllication.model
import java.io.Serializable

import com.google.gson.annotations.SerializedName


data class Office(
    @SerializedName("id")
    val office_id: String,
    @SerializedName("name")
    val office_name: String,
    val map: String
):Serializable