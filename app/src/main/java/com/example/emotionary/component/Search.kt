package com.example.emotionary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emotionary.R
import com.example.emotionary.data.DiaryListItem
import com.example.emotionary.data.IntToImgEmotion

@Composable
fun Search(
    search: String,
    onSearchChanged: (String) -> Unit,
    searchHasFocus: Boolean,
    onFocusChanged: (Boolean) -> Unit
) {
    val focusRequester = FocusRequester()
    val label = "다시 보고 싶은 일기를 검색해보세요."

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp, RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_search_gray),
            contentDescription = "search",
            modifier = Modifier.size(20.dp)
        )
        if (!searchHasFocus && search.isEmpty()) {
            Text(
                text = label,
                color = Color.Gray,
                modifier = Modifier.clickable {
                    focusRequester.requestFocus()
                }
            )
        }

        BasicTextField(
            value = search,
            onValueChange = onSearchChanged,
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
            singleLine = true,
        )
    }
}

@Composable
fun SearchItemCard(item: DiaryListItem, onClick: () -> Unit) {
    val emotionDrawable = IntToImgEmotion(item.diaryEmotion)
    Card(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.main_gray),
                shape = RoundedCornerShape(12.dp)
            )
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 13.dp, start = 15.dp, bottom = 13.dp, end = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = emotionDrawable),
                        contentDescription = "감정",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))

                    Text(
                        text = "${item.diaryDate.monthValue}월 ${item.diaryDate.dayOfMonth}일 일기",
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = item.diaryTitle,
                    fontSize = 13.sp
                )
            }
        }

    }
}