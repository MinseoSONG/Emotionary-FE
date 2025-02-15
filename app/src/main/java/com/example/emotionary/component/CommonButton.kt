package com.example.emotionary.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonButton(
    label: String,
    corner: Int,
    fontSize: Int,
    color: Int,
    fontColor: Int,
    paddingValues: Int,
    modifier : Modifier,
    onClick: () ->Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(paddingValues.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = color)),
        shape = RoundedCornerShape(corner.dp),
        modifier = modifier
    ){
        Text(
            text = label,
            fontSize = fontSize.sp,
            color = colorResource(id = fontColor)
        )
    }
}

@Composable
fun ChangeButton(
    label: String,
    corner: Int,
    fontSize: Int,
    color: () -> Int,
    fontColor: () -> Int,
    onClick: () ->Unit
) {
    Button(
        onClick = onClick,
        contentPadding = PaddingValues(12.dp),
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(colorResource(id = color())),
        shape = RoundedCornerShape(corner.dp)
    ){
        Text(
            text = label,
            fontSize = fontSize.sp,
            color = colorResource(id = fontColor())
        )
    }
}