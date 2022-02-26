package com.example.deskbookingappllication.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://deskbooking.dev.webundsoehne.com/api/"

object RetrofitInstance {

    private fun getHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(getHttpClient())
            .build()
    }

    val deskApi:DeskApi by lazy {
        retrofit.create(DeskApi::class.java)
    }
    val userApi:UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
    val officeApi:OfficeApi by lazy {
        retrofit.create(OfficeApi::class.java)
    }

}