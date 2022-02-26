package com.example.deskbookingappllication.model.api

import com.example.deskbookingappllication.model.Office
import retrofit2.Response
import retrofit2.http.GET

interface OfficeApi {

    @GET("office")
    suspend fun getOfficeList(): Response<List<Office>>
}