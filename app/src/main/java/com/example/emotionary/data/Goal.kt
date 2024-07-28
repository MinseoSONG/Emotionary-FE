package com.example.emotionary.data

import java.time.LocalDate

data class Goal(
    val goalTitle: String, // 목표 주제
    val goalMain: Boolean, // 대표 목표 확인
    val goalCheckList: List<checkList>, // 목표 체크리스트
    val goalStart: String, // 시작 날짜
    val goalEnd: String, // 종료 날짜
    val goalProgress: Float // 목표 진행도
)

data class GoalHome(
    val goalTitle: String,
    val goalStart: String,
    val goalEnd: String,
    val goalProgress: Float
)

data class checkList(
    val checkListTitle: String,
    val checkListCheck: Boolean
)