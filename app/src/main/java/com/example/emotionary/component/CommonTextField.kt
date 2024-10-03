package com.example.emotionary.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.emotionary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTextField(
    value: String,
    onValueChange : (String)->Unit,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray_200),
            unfocusedBorderColor = colorResource(id = R.color.gray_200),
            focusedLabelColor = colorResource(id = R.color.main_gray_dark),
            unfocusedLabelColor = colorResource(id = R.color.main_gray_dark)
        ),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PassWordTextField(
    value: String,
    onValueChange : (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
    imeAction: ImeAction
) {
    var shouldShowPassword by remember { mutableStateOf(false) }

    val passwordResource: (Boolean) -> Int = {
        if (it) {
            R.drawable.baseline_remove_red_eye_24 // 패스워드가 보일 때
        } else {
            R.drawable.baseline_visibility_off_24 // 패스워드가 가려질 때
        }
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.gray_200),
            unfocusedBorderColor = colorResource(id = R.color.gray_200),
            focusedLabelColor = colorResource(id = R.color.main_gray_dark),
            unfocusedLabelColor = colorResource(id = R.color.main_gray_dark)
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    shouldShowPassword = !shouldShowPassword // 클릭 시 패스워드 가시성 전환
                }
            ) {
                Icon(
                    painter = painterResource(id = passwordResource(shouldShowPassword)),
                    contentDescription = "눈 아이콘"
                )
            }
        },
        visualTransformation = if (shouldShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    )
}