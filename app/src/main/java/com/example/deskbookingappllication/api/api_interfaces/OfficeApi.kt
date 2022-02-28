package com.example.deskbookingappllication.api.api_interfaces

import android.media.session.MediaSession
import com.example.deskbookingappllication.model.Office
import retrofit2.Response
import retrofit2.http.GET

interface OfficeApi {

    @GET("office")
    suspend fun getOfficeList(): Response<List<Office>>
}