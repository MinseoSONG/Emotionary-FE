package com.example.emotionary.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.emotionary.screen.diary.DiaryScreen
import com.example.emotionary.screen.home.HomeScreen
import com.example.emotionary.screen.mypage.MyPageScreen
import com.example.emotionary.screen.search.SearchDetailScreen
import com.example.emotionary.screen.search.SearchScreen
import com.example.emotionary.screen.start.LoginScreen
import com.example.emotionary.screen.start.SignupScreen_ID
import com.example.emotionary.screen.start.SignupScreen_Nickname
import com.example.emotionary.screen.start.SplashScreen
import java.time.LocalDate

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Search"){
        // start
        // 스플래쉬
        composable(route = "Splash"){
            SplashScreen(navController)
        }
        // 로그인
        composable(route = "Login"){
            LoginScreen(navController)
        }
        // 회원가입_1
        composable(route = "Signup1"){
            SignupScreen_ID(navController)
        }
        // 회원가입_2
        composable(route = "Signup2"){
            SignupScreen_Nickname(navController)
        }

        // home
        // 홈
        composable(route = "Home"){
            HomeScreen(navController)
        }

        // diary
        // 다이어리
        composable(route = "Diary"){
            DiaryScreen(navController)
        }

        // search
        // 검색
        composable(route = "Search"){
            SearchScreen(navController)
        }
        // 상세보기
        composable(
            route = "SearchDetail/{diaryID}/{todoDate}",
            arguments = listOf(
                navArgument("diaryID"){type = NavType.StringType},
                navArgument("todoDate"){type = NavType.StringType}
            )
        ) {
            val diaryID = it.arguments?.getString("diaryID") ?: return@composable
            val todoDate = it.arguments?.getString("todoDate") ?: return@composable
            SearchDetailScreen(diaryID, todoDate)
        }

        // mypage
        // 마이페이지
        composable(route = "MyPage"){
            MyPageScreen(navController)
        }
    }
}