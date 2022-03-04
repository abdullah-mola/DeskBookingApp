package com.example.deskbookingappllication.api.api_interfaces

import com.example.deskbookingappllication.model.Book
import com.example.deskbookingappllication.model.Comment
import com.example.deskbookingappllication.model.Desk
import com.example.deskbookingappllication.model.DeskEquipmentDetails
import retrofit2.Response
import retrofit2.http.*

interface DeskApi {

    @GET("desk")
    suspend fun getListOfDesks(): Response<List<Desk>>

    @GET("favourite")
    suspend fun getListOfFavouriteDesks(): Response<List<Desk>>

    @GET("desk")
    suspend fun getListOfDesksByOfficeId(@Query("office") id: String): Response<List<Desk>>

    @GET("desk/{deskId}/comment")
    suspend fun getListOfComments(@Path("deskId") id: String?): Response<List<Comment>>

    @GET("/desk/{deskId}")
    suspend fun getDeskDetails(@Path("deskId") id: String?): Response<DeskEquipmentDetails>

    @POST("booking")
    suspend fun bookADesk(@Body body: Book): Response<Book>
}