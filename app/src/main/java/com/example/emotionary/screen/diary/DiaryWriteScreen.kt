package com.example.emotionary.screen.diary

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DiaryWriteScreen(diaryDate: String, navController: NavHostController) {
    Text(text = "다이어리 추가, 수정 스크린")
}