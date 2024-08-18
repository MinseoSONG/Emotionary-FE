package com.example.emotionary.data.api

data class LoginRequest(
    val userID: String,
    val userPassword: String
)

data class LoginResponse(
    val token: Token,
    val user: LoginUser,
    val message: String,
    val code: Int
)

data class Token(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken
)

data class AccessToken(
    val token: String,
    val expiresIn: Float
)

data class RefreshToken(
    val token: String,
    val expiresIn: Float
)

data class LoginUser(
    val userID: String,
    val userName: String,
    val userProfile: String
)