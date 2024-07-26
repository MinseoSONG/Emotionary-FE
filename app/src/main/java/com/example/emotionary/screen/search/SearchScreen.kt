package com.example.emotionary.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.emotionary.component.Appbar
import com.example.emotionary.component.SearchItemCard
import com.example.emotionary.component.Search
import com.example.emotionary.component.TopLogo
import com.example.emotionary.viewmodel.DiaryViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun SearchScreen(navController: NavHostController) {
    val diaryViewModel : DiaryViewModel = viewModel()
    
    // 검색 창
    var search by rememberSaveable {
        mutableStateOf("")
    }
    var searchHasFocus by rememberSaveable {
        mutableStateOf(false)
    }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopLogo()
        },
        bottomBar = {
            Appbar(selected = 3, navController = navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 20.dp)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "내 일기 검색하기",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(30.dp))

                Search(
                    search = search,
                    onSearchChanged = { search = it },
                    searchHasFocus = searchHasFocus,
                    onFocusChanged = { searchHasFocus = it }
                )
                Spacer(modifier = Modifier.height(30.dp))

                Divider()
                Spacer(modifier = Modifier.height(30.dp))

                // 검색어에 따라 필터링된 리스트 생성
                val filteredList = diaryViewModel.diaryListItem.filter { item ->
                    item.diaryTitle.contains(search, ignoreCase = true)
                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    itemsIndexed(filteredList){_, item ->
                        SearchItemCard(item) {
                            // 클릭하면 다이어리 화면 이동
                            navController.navigate("")
                        }
                    }
                }
            }
        }
    }
}