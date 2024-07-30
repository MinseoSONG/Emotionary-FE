package com.example.emotionary.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.emotionary.data.DiaryListItem
import com.example.emotionary.data.Todo
import com.example.emotionary.data.checkList
import java.time.LocalDate

class TodoViewModel : ViewModel() {
    var diaryInfo = mutableStateListOf<Todo>()
    init {
        diaryInfo.addAll(
            listOf(
                Todo(
                    todoCategory = "집안일",
                    todoCheckList = mutableStateListOf(
                        checkList(checkListTitle = "설거지하기", checkListCheck = mutableStateOf(false)),
                        checkList(checkListTitle = "빨래하기", checkListCheck = mutableStateOf(true))
                    )
                ),
                Todo(
                    todoCategory = "프로젝트",
                    todoCheckList = mutableStateListOf(
                        checkList(checkListTitle = "와이어 프레임 디자인", checkListCheck = mutableStateOf(true)),
                        checkList(checkListTitle = "UI/UX 구현", checkListCheck = mutableStateOf(false)),
                        checkList(checkListTitle = "홈화면 구현", checkListCheck = mutableStateOf(false))
                    )
                )
            )
        )
    }

    fun toggleCheck(todoCategory: String, checkListTitle: String) {
        val todo = diaryInfo.find { it.todoCategory == todoCategory }
        todo?.let {
            val checkListItem = it.todoCheckList.find { it.checkListTitle == checkListTitle }
            checkListItem?.let { item ->
                item.checkListCheck.value = !item.checkListCheck.value
            }
        }
    }
}