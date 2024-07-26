package com.example.emotionary.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.emotionary.data.DiaryListItem
import java.time.LocalDate

class DiaryViewModel: ViewModel() {
    var diaryListItem = mutableStateListOf<DiaryListItem>()
    init {
        diaryListItem.addAll(
            listOf(
                DiaryListItem(
                    diaryID = "1",
                    diaryTitle = "오늘 오버워치 연승함",
                    diaryDate = LocalDate.parse("2024-05-18"),
                    diaryEmotion = 5
                ),
                DiaryListItem(
                    diaryID = "2",
                    diaryTitle = "오늘 오버워치 연패함",
                    diaryDate = LocalDate.parse("2024-07-19"),
                    diaryEmotion = 1
                )
            )
        )
    }
}