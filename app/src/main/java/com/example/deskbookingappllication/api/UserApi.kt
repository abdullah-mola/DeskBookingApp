package com.example.deskbookingappllication.api

import com.example.deskbookingappllication.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("register")
    suspend fun userRegister(@Body body: User): Response<User>

    @POST("login")
    suspend fun userLogin(@Body request: LoginRequestBody): Response<LoginResponse>

}