package com.example.emotionary.data

data class User(
    val userID: String, // 아이디
    val userPassword: String, // 비밀번호
    val userName: String, // 닉네임
    val userProfile: String // 사진 url
)

// 홈화면
data class HomeInfo(
    val userName: String,
    val mainGoal: GoalHome?,
    val diary: List<DiaryHome?>
)