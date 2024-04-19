package com.example.data.model

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class SignUpParam(
    private val loginId: String,
    private val name: String,
    private val password: String,
    private val extraUserInfo: String,
    private val profileFilePath: String
) {
    
    fun toRequestBody(): RequestBody {
        return Gson().toJson(this).toRequestBody()
    }
}
