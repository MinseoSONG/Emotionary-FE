package com.example.emotionary.data

import java.time.LocalDate

data class Diary(
    val diaryID: String, // 일기 아이디
    val diaryDate: String, // 날짜
    val diaryEmotion: Int, // 감정
    val diaryTitle: String, // 일기 제목
    val diaryDetail: String, // 일기 내용
    val diaryImage: List<String> // 사진 url 리스트
)

// 홈 화면
data class DiaryHome(
    val diaryID: String,
    val diaryDate: String,
    val diaryEmotion: Int,
    val diaryTitle: String
)

// 검색화면
data class DiaryListItem(
    val diaryID: String,
    val diaryTitle: String,
    val diaryEmotion: Int,
    val diaryDate: LocalDate
)