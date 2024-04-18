package com.example.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class LoginParam(
    @SerializedName("loginId")
    val loginId: String,
    @SerializedName("password")
    val password: String,
) {
    fun toRequestBody(): RequestBody {
        return Gson().toJson(this).toRequestBody()
    }
}
