package com.example.emotionary.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emotionary.R
import com.example.emotionary.data.checkList
import com.example.emotionary.viewmodel.TodoViewModel

@Composable
fun TodoTab(todoCategory: String, todoCheckList: SnapshotStateList<checkList>, todoViewModel: TodoViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        // 할일 카테고리
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.gray_100))
                    .height(30.dp)
                    .padding(start = 10.dp, end = 10.dp)
                    .wrapContentWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = todoCategory,
                    fontSize = 12.sp
                )
            }

            Image(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "삭제"
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        // 할일 체크리스트
        todoCheckList.forEach { checkItem ->
            var isChecked by remember { checkItem.checkListCheck }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(25.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(
                        id = if (isChecked) R.drawable.ic_checkbox_check else R.drawable.ic_checkbox_none
                    ),
                    contentDescription = "체크박스",
                    modifier = Modifier
                        .size(15.dp) // 원하는 크기로 조절
                        .clickable {
                            todoViewModel.toggleCheck(todoCategory, checkItem.checkListTitle)
                        }
                )

                Spacer(modifier = Modifier.width(8.dp)) // 체크박스와 텍스트 사이의 간격

                Text(
                    text = checkItem.checkListTitle,
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // 점선 배경
                Canvas(modifier = Modifier.matchParentSize()) {
                    val strokeWidth = 1.dp.toPx()
                    val lineColor = Color.Gray

                    drawLine(
                        color = lineColor,
                        start = Offset(0f, 0F),
                        end = Offset(size.width, 0F),
                        strokeWidth = strokeWidth,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
                    )
                }
            }
        }
    }
}