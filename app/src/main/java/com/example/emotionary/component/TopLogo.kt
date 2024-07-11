package com.example.emotionary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.emotionary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopLogo() {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(id = R.drawable.emotionary_logo_text),
                contentDescription = "로고 이미지",
                modifier = Modifier.size(width = 90.dp, height = 20.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}