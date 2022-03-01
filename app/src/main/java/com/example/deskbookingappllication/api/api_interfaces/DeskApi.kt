package com.example.deskbookingappllication.api.api_interfaces

import com.example.deskbookingappllication.model.Comment
import com.example.deskbookingappllication.model.Desk
import retrofit2.Response
import retrofit2.http.GET

interface DeskApi {

    @GET("desk")
    suspend fun getListOfDesks(): Response<List<Desk>>

    @GET("favourite")
    suspend fun getListOfFavouriteDesks(): Response<List<Desk>>

    @GET ("comment")
    suspend fun getListOfComments(): Response<List<Comment>>

}