package com.example.emotionary.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.CommonButton

@Composable
fun SplashScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.emotionary_logo_text),
                    contentDescription = "로고",
                    modifier = Modifier.size(180.dp,35.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.emotionary_logo),
                    contentDescription = "로고 이미지",
                    modifier = Modifier
                        .size(165.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            CommonButton(
                label = "아이디 로그인",
                corner = 8,
                fontSize = 16,
                color = R.color.button_gray,
                fontColor = R.color.black,
                paddingValues = 12,
                modifier = Modifier.fillMaxWidth()
            ) {
                // 로그인 버튼
                navController.navigate("Login")
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "회원가입",
                fontSize = 16.sp,
                color = colorResource(id = R.color.dark_gray),
                modifier = Modifier
                    .clickable {
                        // 회원가입 버튼
                        navController.navigate("Signup1")
                    }
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}