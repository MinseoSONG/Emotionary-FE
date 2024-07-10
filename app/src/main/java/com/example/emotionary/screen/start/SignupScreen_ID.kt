package com.example.emotionary.screen.start

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.ChangeButton
import com.example.emotionary.component.CommonButton
import com.example.emotionary.component.CommonTextField

@Composable
fun SignupScreen_ID(navController: NavHostController) {
    var userID by remember {
        mutableStateOf("")
    }
    var userPassWord by remember {
        mutableStateOf("")
    }
    var checkuserPassWord by remember {
        mutableStateOf("")
    }
    var idtext by remember{
        // 사용 가능한 아이디입니다. , 이미 존재하는 아이디입니다.
        mutableStateOf("중복을 확인해주세요.")
    }
    var passwordtext by remember{
        // 비밀번호가 일치합니다. , 비밀번호가 일치하지 않습니다.
        mutableStateOf("")
    }
    val isButtonEnabled = userID.isNotEmpty() && userPassWord.isNotEmpty() && checkuserPassWord.isNotEmpty()
    val context = LocalContext.current

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
            Spacer(modifier = Modifier.height(30.dp))

            Column {
                CommonTextField(
                    value = userID,
                    onValueChange = {userID = it},
                    label = "아이디",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text( // 중복 확인 text
                        text = idtext,
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.main_gray_dark)
                    )
                    CommonButton(
                        label = "중복 확인",
                        corner = 8,
                        fontSize = 10,
                        color = R.color.main_yellow,
                        fontColor = R.color.black,
                        paddingValues = 5,
                        modifier = Modifier.width(100.dp)
                    ) {
                        // 중복 확인 버튼
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                CommonTextField(
                    value = userPassWord,
                    onValueChange = {userPassWord = it},
                    label = "비밀번호",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                )
                CommonTextField(
                    value = checkuserPassWord,
                    onValueChange = {checkuserPassWord = it},
                    label = "비밀번호 확인",
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                )
                Spacer(modifier = Modifier.height(10.dp))

                if(checkuserPassWord == ""&& userPassWord == ""){
                    passwordtext = ""
                }
                else if(checkuserPassWord == userPassWord){
                    passwordtext = "비밀번호가 일치합니다."
                }
                else{
                    passwordtext = "비밀번호가 일치하지 않습니다."
                }

                Text( // 비밀번호 확인 text
                    text = passwordtext,
                    fontSize = 13.sp,
                    color = colorResource(id = R.color.main_gray_dark)
                )

            }
        }
        Spacer(modifier = Modifier.height(70.dp))

        ChangeButton(
            label = "다음으로",
            corner = 8,
            fontSize = 16,
            color = { if(isButtonEnabled) R.color.black else R.color.button_gray },
            fontColor = { if(isButtonEnabled) R.color.white else R.color.black }
        ) {
            // 회원가입_2로 이동
            if(isButtonEnabled) // 활성화
                navController.navigate("Signup2")
            else if(userID.isNotEmpty() && userPassWord.isEmpty() && checkuserPassWord.isNotEmpty()) // 비활성화
                Toast.makeText(context, "비밀번호 입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
            else if(userID.isEmpty() && userPassWord.isNotEmpty() && checkuserPassWord.isNotEmpty())
                Toast.makeText(context, "아이디 입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
            else if(userID.isNotEmpty() && userPassWord.isNotEmpty() && checkuserPassWord.isEmpty())
                Toast.makeText(context, "비밀번호 확인 입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
            else if(passwordtext == "비밀번호가 일치하지 않습니다.")
                Toast.makeText(context, passwordtext, Toast.LENGTH_SHORT).show()
            else if(idtext == "중복을 확인해주세요.")
                Toast.makeText(context, "아이디 "+idtext, Toast.LENGTH_SHORT).show()
            else if(idtext == "이미 존재하는 아이디입니다.")
                Toast.makeText(context, "아이디를 변경해주세요.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "입력창이 비어있습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}