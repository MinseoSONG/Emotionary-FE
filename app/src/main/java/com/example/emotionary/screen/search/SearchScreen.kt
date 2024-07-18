package com.example.emotionary.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
fun SearchScreen(navController: NavHostController) {
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopLogo()
        },
        bottomBar = {
            Appbar(selected = 3, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "내 일기 검색하기",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}