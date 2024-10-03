package com.example.emotionary.screen.start

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.ChangeButton
import com.example.emotionary.component.CommonTextField
import com.example.emotionary.component.PassWordTextField
import com.example.emotionary.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController) {
    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    val isButtonEnabled = userID.isNotEmpty() && userPassWord.isNotEmpty()
    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("LoginInfo", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val loginViewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(130.dp))
        Column {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.emotionary_logo_text),
                    contentDescription = "로고",
                    modifier = Modifier.size(180.dp,35.dp)
                )
                Text(
                    text = "당신을 위한 감정 기록 보관소를 시작해보세요!",
                    color = colorResource(id = R.color.main_gray_dark),
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            Column {
                CommonTextField(
                    value = userID,
                    onValueChange = {userID = it},
                    label = "아이디",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
                PassWordTextField(
                    value = userPassWord,
                    onValueChange = {userPassWord = it},
                    label = "비밀번호",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
                Spacer(modifier = Modifier.height(20.dp))
                ChangeButton(
                    label = "로그인",
                    corner = 8,
                    fontSize = 16,
                    color = { if(isButtonEnabled) R.color.black else R.color.gray_100 },
                    fontColor = { if(isButtonEnabled) R.color.white else R.color.black }
                ) {
                    // 로그인 버튼
                    if(isButtonEnabled) { // 활성화
                        loginViewModel.initializeState()
                        loginViewModel.postLogin(userID, userPassWord)
                    }
                    else if(userID.isNotEmpty() && userPassWord.isEmpty()) // 비활성화
                        Toast.makeText(context, "비밀번호 입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
                    else if(userID.isEmpty() && userPassWord.isNotEmpty())
                        Toast.makeText(context, "아이디 입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(context, "입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
                }

                LaunchedEffect(loginViewModel.success) {
                    if(loginViewModel.success == true){
                        editor.putString("userID", userID)
                        editor.putString("userPassword", userPassWord)
                        editor.apply()
                        Toast.makeText(context, "${loginViewModel.loginInfo.user.userName}님 환영합니다!",Toast.LENGTH_SHORT).show()
                        navController.navigate("Home")
                    }
                    else if(loginViewModel.success == false){
                        Toast.makeText(context, loginViewModel.message, Toast.LENGTH_SHORT).show()
                        Log.d("Login", "Login Failed")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "회원가입",
                fontSize = 14.sp,
                color = colorResource(id = R.color.gray_800),
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        // 회원가입 페이지
                        navController.navigate("Signup1")
                    }
            )
            Text(
                text = "아이디찾기",
                fontSize = 14.sp,
                color = colorResource(id = R.color.gray_800),
                modifier = Modifier.weight(1f)
            )
            Text(
                text = "비밀번호찾기",
                fontSize = 14.sp,
                color = colorResource(id = R.color.gray_800)
            )
        }
    }
}