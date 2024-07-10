package com.example.emotionary.screen.start

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emotionary.R
import com.example.emotionary.component.ChangeButton
import com.example.emotionary.component.CommonButton
import com.example.emotionary.component.CommonTextField


@Composable
fun SignupScreen_Nickname(navController: NavHostController) {
    var userNickname by remember {
        mutableStateOf("")
    }
    var nicknametext by remember{
        // 사용 가능한 닉네임입니다. , 이미 존재하는 닉네임입니다.
        mutableStateOf("중복을 확인해주세요.")
    }
    val isButtonEnabled = userNickname.isNotEmpty()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
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
            
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "프로필 사진 (선택)",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.main_gray_dark),
                    textAlign = TextAlign.Left
                )
                Spacer(modifier = Modifier.height(15.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_photo),
                        contentDescription = "이미지 등록",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    
                    CommonButton(
                        label = "이미지 등록",
                        corner = 12,
                        fontSize = 14,
                        color = R.color.button_gray,
                        fontColor = R.color.black,
                        paddingValues = 5,
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = colorResource(id = R.color.gray_400),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .width(100.dp)
                            .height(32.dp)
                    ) {
                        // 프로필 사진 등록
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            
            Column {
                CommonTextField(
                    value = userNickname,
                    onValueChange = {userNickname = it},
                    label = "닉네임",
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
                        text = nicknametext,
                        fontSize = 13.sp,
                        color = colorResource(id = R.color.main_gray_dark)
                    )

                    CommonButton(
                        label = "중복 확인",
                        corner = 8,
                        fontSize = 13,
                        color = R.color.main_yellow,
                        fontColor = R.color.black,
                        paddingValues = 5,
                        modifier = Modifier.width(80.dp)
                    ) {
                        // 중복 확인 버튼
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        ChangeButton(
            label = "가입하기",
            corner = 8,
            fontSize = 16,
            color = { if(isButtonEnabled) R.color.black else R.color.button_gray },
            fontColor = { if(isButtonEnabled) R.color.white else R.color.black }
        ) {
            // 로그인 화면으로 이동
            if(isButtonEnabled){
                if(nicknametext == "사용 가능한 닉네임입니다.") {
                    navController.navigate("Login")
                    Toast.makeText(context, "가입이 성공적으로 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
                else
                    Toast.makeText(context,"이미 존재하는 닉네임입니다.",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"닉네임 입력창이 비어있습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}