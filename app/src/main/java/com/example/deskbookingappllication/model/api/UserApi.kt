package com.example.deskbookingappllication.model.api

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {


    @POST("register")
    suspend fun userRegister(@Body body: RequestBody)

    @POST("login")
    suspend fun userLogin(@Body request: LoginRequestBody): Response<LoginResponse>

}