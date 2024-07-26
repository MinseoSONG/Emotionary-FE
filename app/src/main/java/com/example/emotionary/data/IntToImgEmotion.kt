package com.example.emotionary.data

import com.example.emotionary.R

fun IntToImgEmotion(diaryEmotion: Int): Int{
    return when(diaryEmotion){
        1 -> R.drawable.img_emotion_angry
        2 -> R.drawable.img_emotion_sad
        3 -> R.drawable.img_emotion_soso
        4 -> R.drawable.img_emotion_happy
        5 -> R.drawable.img_emotion_perfect
        else -> R.drawable.img_emotion_happy
    }
}