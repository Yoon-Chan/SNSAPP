package com.example.data.model

import com.google.gson.Gson
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class CommentParam(
    val comment: String
) {
    fun toRequestBody() : RequestBody {
        return Gson().toJson(this).toRequestBody()
    }
    
}
