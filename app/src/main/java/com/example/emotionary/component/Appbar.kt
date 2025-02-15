package com.example.emotionary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emotionary.R
import java.time.LocalDate

@Composable
fun Appbar(
    selected:Int,
    navController: NavHostController
) {
    val imageSize: Dp = 20.dp
    val fontSize = 10.sp
    val today = LocalDate.now() // 오늘
    val stringToday = today.toString()

    var homeImage = R.drawable.ic_home_gray
    var homeColor = R.color.main_gray
    var diaryImage = R.drawable.ic_diary_gray
    var diaryColor = R.color.main_gray
    var searchImage = R.drawable.ic_search_gray
    var searchColor = R.color.main_gray
    var myPageImage = R.drawable.ic_profile_gray
    var myPageColor = R.color.main_gray

    when (selected){
        1-> {
            homeImage = R.drawable.ic_home_black
            homeColor = R.color.black
        }
        2 -> {
            diaryImage = R.drawable.ic_diary_black
            diaryColor = R.color.black
        }
        3 -> {
            searchImage = R.drawable.ic_search_black
            searchColor = R.color.black
        }
        4 -> {
            myPageImage = R.drawable.ic_profile_black
            myPageColor = R.color.black
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, shape = RectangleShape),
        contentAlignment = Alignment.BottomCenter
    ){
        Column {
            Divider(
                color = colorResource(id = R.color.gray_200)
            )

            Row(
                modifier = Modifier
//                    .clickable{}
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(80.dp)
                ,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ){
                CreateTap(navController, "Home", homeImage, homeColor, imageSize, fontSize, "홈")
                CreateTap(navController, "Diary/$stringToday", diaryImage, diaryColor, imageSize, fontSize, "다이어리")
                CreateTap(navController, "Search", searchImage, searchColor, imageSize, fontSize, "검색")
                CreateTap(navController, "MyPage", myPageImage, myPageColor, imageSize, fontSize, "MY")
            }
        }
    }
}

@Composable
fun CreateTap(navController: NavHostController, route:String, image:Int, textColor:Int, imageSize: Dp, fontSize: TextUnit, text:String){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable {
                navController.navigate(route)
            }
            .size(80.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
        Text(
            text = text,
            fontSize = fontSize,
            color = colorResource(id = textColor)
        )
    }
}