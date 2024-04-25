package com.example.data.model

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class UpdateMyInfoParam(
    val userName: String,
    val extraUserInfo: String,
    val profileFilePath: String
){
    fun toRequestBody() : RequestBody {
        return Gson().toJson(this).toRequestBody()
    }
}
