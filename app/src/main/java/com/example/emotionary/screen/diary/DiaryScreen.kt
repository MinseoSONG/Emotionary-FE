package com.example.emotionary.screen.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emotionary.component.Appbar
import com.example.emotionary.component.TopLogo
import java.time.LocalDate

@Composable
fun DiaryScreen(diaryDate: String, navController: NavHostController) {
    val today = LocalDate.now() // 오늘
    val diaryDate = LocalDate.parse(diaryDate)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopLogo()
        },
        bottomBar = {
            Appbar(selected = 2, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if(diaryDate==today)"오늘" else "${diaryDate.monthValue}월 ${diaryDate.dayOfMonth}일",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "의 일기",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}