package com.example.deskbookingappllication.model

data class User(
    val email: String?,
    val password: String?,
    val firstname: String?,
    val lastname: String?,
    val department: String?,
    val isAdmin: Boolean?
) {
    constructor(
        email:String?,
        password:String?,
        firstname:String?,
        lastname:String?,
        department:String?

    ) : this(email,password,firstname,lastname,department,null)
}