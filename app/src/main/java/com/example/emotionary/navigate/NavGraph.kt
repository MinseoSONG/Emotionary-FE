package com.example.emotionary.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emotionary.screen.start.LoginScreen
import com.example.emotionary.screen.start.SignupScreen_ID
import com.example.emotionary.screen.start.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Signup1"){
        // 시작 start
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

        }
    }
}