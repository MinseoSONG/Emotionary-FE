package com.example.emotionary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.emotionary.data.Diary
import com.example.emotionary.data.DiaryListItem
import java.time.LocalDate

class DiaryViewModel: ViewModel() {
    var diaryListItem = mutableStateListOf<DiaryListItem>()
    var diary by mutableStateOf<Diary?>(
        Diary(
            diaryID = "1",
            diaryDate = "2024-07-28",
            diaryEmotion = 2,
            diaryTitle = "에버랜드 갔다옴",
            diaryDetail = "에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴,에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴, 에버랜드 갔다옴",
            diaryImage = emptyList()
        )
    )
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