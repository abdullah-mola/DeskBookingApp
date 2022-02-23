package com.example.deskbookingappllication.model.api

import com.example.deskbookingappllication.model.room.entities.User
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserApi {


    @POST("register")
    suspend fun userRegister(@Body body: RequestBody)

    @POST("login")
    suspend fun userLogin(@Body request:LoginRequestBody):LoginResponse

}