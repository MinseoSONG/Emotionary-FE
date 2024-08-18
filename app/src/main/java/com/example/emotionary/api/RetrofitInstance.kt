package com.example.emotionary.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: APIService by lazy{
        Retrofit.Builder()
            .baseUrl("http://13.125.198.104")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)
    }
}