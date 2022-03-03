package com.example.deskbookingappllication.model

data class User(
    val email: String?,
    val password: String?,
    val firstname: String?,
    val lastname: String?,
    val department: String?,
    val isAdmin:Boolean?
)