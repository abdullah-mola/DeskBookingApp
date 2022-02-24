package com.example.deskbookingappllication.model

import com.google.gson.annotations.SerializedName

data class User(
    val email: String?,
    val password: String?,
    val firstname: String?,
    val lastname: String?,
    val department: String?
)
