package com.example.deskbookingappllication.api.api_interfaces

import com.example.deskbookingappllication.api.LoginRequestBody
import com.example.deskbookingappllication.api.LoginResponse
import com.example.deskbookingappllication.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {

    @POST("register")
    suspend fun userRegister(@Body body: User): Response<User>

    @POST("login")
    suspend fun userLogin(@Body request: LoginRequestBody): Response<LoginResponse>

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") id: String): Response<User>

    @POST("user")
    suspend fun updateUser():Response<User>

}