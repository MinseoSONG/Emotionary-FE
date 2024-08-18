package com.example.emotionary.api

import com.example.emotionary.data.api.LoginRequest
import com.example.emotionary.data.api.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {
    // 로그인
    @POST("/api/auth/login")
    suspend fun postLogin(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}