package com.example.emotionary.data

import androidx.compose.runtime.MutableState

data class checkList(
    val checkListTitle: String, // 제목
    var checkListCheck: MutableState<Boolean> // 체크 확인
)