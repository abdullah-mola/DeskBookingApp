package com.example.deskbookingappllication.api

import com.example.deskbookingappllication.model.Desk
import retrofit2.http.GET

interface DeskApi {

    @GET("/desk")
    suspend fun getDesks(): List<Desk>

}