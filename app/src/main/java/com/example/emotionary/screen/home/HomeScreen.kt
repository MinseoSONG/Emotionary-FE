package com.example.emotionary.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.Appbar
import com.example.emotionary.component.TopLogo
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val userNickname = "박뚝딱" // 닉네임
    val targetTitle = "자격증 시험" // 목표 제목
    val targetStart = LocalDate.parse("2024-07-11") // 목표 시작 날짜
    val targetEnd = LocalDate.parse("2024-07-25") // 목표 종료 날짜
    val today = LocalDate.now()

    // D-Day 계산
    val D_Date = ChronoUnit.DAYS.between(today, targetEnd).toInt()

    // 캘린더 띄울 달
    var month by remember{
        mutableStateOf(12)
    }

    val progress = 0.65f

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopLogo()
        },
        bottomBar = {
            Appbar(selected = 1, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(paddingValues)
        ) {
            // 프로필
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 30.dp)
                    .height(90.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = "프로필 이미지",
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier= Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ){
                    Row {
                        Text(
                            text = userNickname,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = " 의",
                            fontSize = 24.sp
                        )
                    }
                    Row(
                        modifier = Modifier.height(40.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = if(month==1)painterResource(id = R.drawable.ic_before_gray) else painterResource(id = R.drawable.ic_before_black),
                            contentDescription = "이전 버튼",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    if (month != 1)
                                        month -= 1
                                }
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = month.toString() + "월 일기",
                            fontSize = 24.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))

                        Image(
                            painter = if(month==12)painterResource(id = R.drawable.ic_next_gray) else painterResource(id = R.drawable.ic_next_black),
                            contentDescription = "이전 버튼",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    if (month != 12)
                                        month += 1
                                }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            // 목표 진행도
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(80.dp)
            ){
                Row(
                    modifier = Modifier.height(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "목표 진행도",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = "D-"+ if(D_Date == 0)"Day" else D_Date.toString(),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.main_gray)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = targetTitle,
                            fontSize = 12.sp
                        )

                        Text(
                            text = targetStart.toString() + " ~ " + targetEnd.toString(),
                            fontSize = 10.sp,
                            color = colorResource(id = R.color.main_gray)
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .shadow(5.dp, RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp)) // 모서리 반경 설정
                            .background(colorResource(id = R.color.main_gray)) // 배경 색상 설정
                            .padding(3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progress)
                                .fillMaxHeight()
                                .clip(RoundedCornerShape(10.dp))
                                .background(colorResource(id = R.color.main_yellow))
                        )
                    }

                }
            }
        }
    }
}