package com.example.emotionary.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emotionary.api.RetrofitInstance
import com.example.emotionary.data.api.AccessToken
import com.example.emotionary.data.api.LoginRequest
import com.example.emotionary.data.api.LoginResponse
import com.example.emotionary.data.api.LoginUser
import com.example.emotionary.data.api.RefreshToken
import com.example.emotionary.data.api.Token
import kotlinx.coroutines.launch

class LoginViewModel(): ViewModel() {
    var success by mutableStateOf<Boolean?>(null)
    var message by mutableStateOf<String?>(null)

    var loginInfo by mutableStateOf<LoginResponse>(
        LoginResponse(
            token = Token(
                accessToken = AccessToken(token = "", expiresIn = 0f),
                refreshToken = RefreshToken(token = "", expiresIn = 0f)
            ),
            user = LoginUser(userID = "", userName = "", userProfile = ""),
            message = "",
            code = 0
        )
    )

    fun initializeState(){
        success = null
        message = null
    }

    fun postLogin(
        userID: String,
        userPassword:String
    ){
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(userID, userPassword)
                val response = RetrofitInstance.api.postLogin(loginRequest)
                if (response.message == "로그인에 성공하였습니다."){
                    loginInfo = response
                    message = response.message
                    success = true
                }else{
                    message = response.message
                    success = false
                }
            }catch (e:Exception){
                Log.d("Login", e.toString())
                e.printStackTrace()
                message = "서버 연결 실패"
                success = false
            }
        }
    }
}