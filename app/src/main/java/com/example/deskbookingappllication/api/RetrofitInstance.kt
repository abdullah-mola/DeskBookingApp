package com.example.deskbookingappllication.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://deskbooking.dev.webundsoehne.com/api/"

object RetrofitInstance {

    var authToken: String? = null



    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            if (authToken != null) {
                val newRequest = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $authToken")
                    .build()
                chain.proceed(newRequest)
            } else {
                chain.proceed(chain.request())
            }

        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
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