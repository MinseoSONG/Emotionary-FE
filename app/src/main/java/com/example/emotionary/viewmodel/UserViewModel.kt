package com.example.emotionary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.emotionary.data.DiaryHome
import com.example.emotionary.data.GoalHome
import com.example.emotionary.data.HomeInfo

class UserViewModel : ViewModel(){
    var homeInfo by mutableStateOf<HomeInfo?>(
        HomeInfo(
            userName = "박뚝딱",
            mainGoal = GoalHome("자격증시험", "2024-07-15", "2024-08-28",0.7f),
            diary = listOf(
                DiaryHome("a1","2024-07-18",5,"옵치 그마간 날"),
                DiaryHome("a2","2024-07-10",2,"옵치 플레간 날"),
                DiaryHome("a3","2024-06-15",1,"화나는 날")
            )
    ))
}