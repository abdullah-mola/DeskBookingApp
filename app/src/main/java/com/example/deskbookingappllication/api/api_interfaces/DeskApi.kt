package com.example.deskbookingappllication.api.api_interfaces

import com.example.deskbookingappllication.model.Comment
import com.example.deskbookingappllication.model.Desk
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DeskApi {

    @GET("desk")
    suspend fun getListOfDesks(): Response<List<Desk>>

    @GET("favourite")
    suspend fun getListOfFavouriteDesks(): Response<List<Desk>>

    @GET("desk")
    suspend fun getListOfDesksByOfficeId(@Query("office") id: String): Response<List<Desk>>

    @GET ("desk/{deskId}/comment")
    suspend fun getListOfComments(@Path("deskId")id:String?): Response<List<Comment>>

}