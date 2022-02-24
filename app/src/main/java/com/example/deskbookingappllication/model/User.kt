package com.example.deskbookingappllication.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("userId")
    val user_id: Int,
    val email: String?,
    val password: Int,
    val firstname: String?,
    val lastname: String?,
    val department: String?
)
