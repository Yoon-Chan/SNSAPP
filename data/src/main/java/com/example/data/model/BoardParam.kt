package com.example.data.model

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody


data class BoardParam(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String
){
    fun toRequestBody() : RequestBody {
        return Gson().toJson(this).toRequestBody()
    }
}
