package com.example.emotionary.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.Appbar
import com.example.emotionary.component.TopLogo
import com.example.emotionary.data.IntToImgEmotion
import com.example.emotionary.viewmodel.UserViewModel
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollstate = rememberScrollState()

    val userViewModel: UserViewModel = viewModel()
    val homeInfo = userViewModel.homeInfo

    val userName = homeInfo?.userName // 닉네임

    // 목표
    val goal = homeInfo?.mainGoal
    val progress = goal?.goalProgress ?: 0f // 목표 진행도
    val goalTitle = goal?.goalTitle ?: "정해진 목표 없음" // 목표 제목
    val goalStart = goal?.goalStart // 목표 시작 날짜
    val goalEnd = goal?.goalEnd // 목표 종료 날짜
    val today = LocalDate.now() // 오늘
    val D_Date = if(goal != null) ChronoUnit.DAYS.between(today, LocalDate.parse(goalEnd)).toInt() else 0 // D-Day 계산

    // 캘린더 띄울 달, 년도
    var month by remember{
        mutableStateOf(today.monthValue)
    }
    var year by remember { mutableStateOf(LocalDate.now().year) }
    var selectedDate by remember { mutableStateOf(today) }

    // 미리보기
    val select = homeInfo?.diary?.find { LocalDate.parse(it?.diaryDate) == selectedDate }
    val diaryTitle = select?.diaryTitle

    // 감정
    var diaryEmotion by remember{ mutableStateOf(R.drawable.img_emotion_happy) } // 선택된 날짜에 해당하는 일기에 띄울 감정
    val emotionMap = remember { mutableStateMapOf<LocalDate, Int>() }

    homeInfo?.diary?.forEach { diary ->
        diary?.let {
            val emotionDrawable = IntToImgEmotion(it.diaryEmotion)
            emotionMap[LocalDate.parse(it.diaryDate)] = emotionDrawable
        }
    }

    select?.let {
        diaryEmotion = emotionMap[selectedDate] ?: R.drawable.img_emotion_happy
    }

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
                    .padding(start = 25.dp, end = 35.dp)
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
                    modifier= Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Row {
                        Text(
                            text = "$userName",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "의 ",
                            fontSize = 24.sp
                        )
                        Text(
                            text = year.toString()+"년",
                            fontSize = 24.sp,
                            color = colorResource(id = R.color.gray_700)
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
                                    if (month == 1) {
                                        month = 12
                                        year -= 1
                                    } else {
                                        month -= 1
                                    }
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
                                    if (month == 12) {
                                        month = 1
                                        year += 1
                                    } else {
                                        month += 1
                                    }
                                }
                        )
                    }
                }
            }

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
                            text = "$goalTitle",
                            fontSize = 12.sp
                        )

                        Text(
                            text = (if(goalStart!=null)goalStart else "") + " ~ " + (if(goalEnd!=null)goalEnd else ""),
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
            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollstate)
            ) {
                // 캘린더
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    // 요일 헤더
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        listOf("월", "화", "수", "목", "금", "토", "일").forEach { day ->
                            Text(text = day, fontSize = 12.sp)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val currentYearMonth = YearMonth.of(year, month)
                        val daysInMonth = currentYearMonth.lengthOfMonth()
                        val firstDayOfMonth = LocalDate.of(year, month, 1)
                        val startDayOfWeek = (firstDayOfMonth.dayOfWeek.value + 6) % 7 // 월요일이 0이 되도록 조정

                        // 빈 칸 추가 (해당 월의 시작 요일에 맞추기 위해)
                        var currentDay = 1 - startDayOfWeek

                        while (currentDay <= daysInMonth) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                for (i in 0..6) {
                                    if (currentDay in 1..daysInMonth) {
                                        val currentDate = LocalDate.of(year, month, currentDay)
                                        val isPast = currentDate.isBefore(today)
                                        val isSelect = currentDate.isEqual(selectedDate)
                                        val emotionDrawable = emotionMap[currentDate]

                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Box(
                                                contentAlignment = Alignment.Center,
                                                modifier = Modifier
                                                    .padding(4.dp)
                                                    .size(33.dp)
                                                    .shadow(5.dp, RoundedCornerShape(8.dp))
                                                    .border(
                                                        width = 1.dp,
                                                        color = if (isSelect) Color.Black else Color.Black.copy(
                                                            alpha = 0.2f
                                                        ),
                                                        shape = RoundedCornerShape(8.dp)
                                                    )
                                                    .clip(RoundedCornerShape(8.dp))
                                                    .background(
                                                        when {
                                                            isPast -> colorResource(id = R.color.gray_300)
                                                            else -> colorResource(id = R.color.gray_100)
                                                        }
                                                    )
                                                    .clickable {
                                                        selectedDate = currentDate
                                                    }
                                            ) {
                                                emotionDrawable?.let {
                                                    Image(
                                                        painter = painterResource(id = it),
                                                        contentDescription = "감정",
                                                        modifier = Modifier
                                                            .size(23.dp)
                                                            .clip(CircleShape),
                                                        contentScale = ContentScale.Crop
                                                    )
                                                }
                                            }
                                            Text(
                                                text = "$currentDay",
                                                fontSize = 10.sp,
                                                fontWeight = if(isSelect) FontWeight.Bold else FontWeight.Normal
                                            )
                                        }
                                    } else {
                                        Box(
                                            modifier = Modifier
                                                .padding(4.dp)
                                                .size(30.dp)
                                        )
                                    }
                                    currentDay++
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 20.dp)
                )

                // 일기 미리보기
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 30.dp)
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "일기 미리보기",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = selectedDate.dayOfMonth.toString() + "일",
                            fontSize = 12.sp,
                            color = colorResource(id = R.color.gray_700)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(35.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .shadow(5.dp, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = R.color.gray_100))
                            .padding(3.dp)
                            .clickable {
                                // 상세보기 페이지로 이동
                                var diaryDate = selectedDate.toString()
                                navController.navigate("Diary/$diaryDate")
                            }
                    ){
                        if(select!=null){
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(id = diaryEmotion),
                                    contentDescription = "감정",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = "$diaryTitle",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}