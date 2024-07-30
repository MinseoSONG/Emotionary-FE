package com.example.emotionary.data

import androidx.compose.runtime.snapshots.SnapshotStateList

data class Todo(
    val todoCategory: String, // 카테고리
    val todoCheckList: SnapshotStateList<checkList> // 체크리스트
)