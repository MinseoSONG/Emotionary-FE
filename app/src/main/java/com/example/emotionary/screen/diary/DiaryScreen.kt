package com.example.emotionary.screen.diary

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.Appbar
import com.example.emotionary.component.TodoTab
import com.example.emotionary.component.TopLogo
import com.example.emotionary.data.IntToImgEmotion
import com.example.emotionary.viewmodel.DiaryViewModel
import com.example.emotionary.viewmodel.TodoViewModel
import java.time.LocalDate

@Composable
fun DiaryScreen(diaryDate: String, navController: NavHostController) {
    val today = LocalDate.now() // 오늘
    val diaryDate = LocalDate.parse(diaryDate)
    val scrollstate = rememberScrollState()

    // 일기
    val diaryViewModel : DiaryViewModel = viewModel()
    val diary = diaryViewModel.diary
    val diaryTitle = diary?.diaryTitle ?: "작성된 일기가 없습니다."
    val diaryDetail = diary?.diaryDetail ?: ""
    val emotionDrawable = IntToImgEmotion(diary?.diaryEmotion ?: 1)

    // 할일
    val todoViewModel : TodoViewModel = viewModel()

    // floating 버튼
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopLogo()
        },
        bottomBar = {
            Appbar(selected = 2, navController = navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { expanded = true },
                containerColor = if(expanded == false) colorResource(id = R.color.main_yellow) else colorResource(id = R.color.gray_300),
                shape = CircleShape,
                modifier = Modifier
                    .size(50.dp)
                    .padding(0.dp)
            ) {
                Image(
                    painter = if(expanded == false) painterResource(id = R.drawable.ic_plus) else painterResource(
                        id = R.drawable.ic_minus
                    ),
                    contentDescription = "버튼",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .padding(paddingValues)
                .verticalScroll(scrollstate)
        ) {
            // 일기
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                // 날짜
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
                Spacer(modifier = Modifier.height(20.dp))

                // 제목 + 감정
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = diaryTitle,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .border(
                                BorderStroke(1.dp, color = colorResource(id = R.color.gray_500)),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ){
                        Image(
                            painter = painterResource(id = emotionDrawable),
                            contentDescription = "감정",
                            modifier = Modifier.size(20.dp)
                        )
                    }

                }

                // 내용
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(184.dp)
                        .padding(top = 10.dp)
                ) {
                    // 점선 배경
                    Canvas(modifier = Modifier.matchParentSize()) {
                        val lineHeight = 20.dp.toPx() // Text의 lineHeight와 동일하게 설정
                        val strokeWidth = 1.dp.toPx()
                        val lineColor = Color.Gray

                        val numLines = (size.height / lineHeight).toInt()
                        for (i in 1..numLines) {
                            val y = i * lineHeight
                            drawLine(
                                color = lineColor,
                                start = androidx.compose.ui.geometry.Offset(0f, y),
                                end = androidx.compose.ui.geometry.Offset(size.width, y),
                                strokeWidth = strokeWidth,
                                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                            )
                        }
                    }
                    Text(
                        text = diaryDetail,
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 20.sp
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))

                // 이미지
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = "diaryImage",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    contentScale = ContentScale.Crop
                )

                // 분리선
                Spacer(modifier = Modifier.height(20.dp))
                Divider()
                Spacer(modifier = Modifier.height(20.dp))
            }

            // 할일
            Column(
                modifier = Modifier.fillMaxWidth()
            ){
                // 날짜
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if(diaryDate==today)"오늘" else "${diaryDate.monthValue}월 ${diaryDate.dayOfMonth}일",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "의 할일",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // 할일 목록 표시
                todoViewModel.diaryInfo.forEach{todo->
                    TodoTab(todoCategory = todo.todoCategory, todoCheckList = todo.todoCheckList, todoViewModel = todoViewModel)
                }

            }

            // floating 버튼
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                offset = DpOffset(280.dp,-120.dp),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
            ) {
                DropdownMenuItem(
                    text = {
                           Text(
                               text = "일기수정",
                               fontSize = 13.sp,
                               color = Color.Gray,
                               fontWeight = FontWeight.Bold,
                               textAlign = TextAlign.Center,
                               modifier = Modifier.fillMaxSize()
                           )
                    },
                    onClick = {
                        // 일기수정 페이지로 이동
                        expanded = false
                        navController.navigate("DiaryWrite/${diaryDate}")
                    },
                    modifier = Modifier
                        .size(90.dp, 20.dp)
                        .align(Alignment.CenterHorizontally)
                )

                DropdownMenuItem(
                    text = {
                        Text(
                            text = "일기쓰기",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onClick = {
                        // 일기쓰기 페이지로 이동
                        expanded = false
                        navController.navigate("DiaryWrite/${diaryDate}")
                    },
                    modifier = Modifier
                        .size(90.dp, 20.dp)
                        .align(Alignment.CenterHorizontally)
                )

                DropdownMenuItem(
                    text = {
                        Text(
                            text = "투두추가",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onClick = {
                        // 투두추가 페이지로 이동
                        expanded = false
                        navController.navigate("Todo/${diaryDate}")
                    },
                    modifier = Modifier
                        .size(90.dp, 20.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}