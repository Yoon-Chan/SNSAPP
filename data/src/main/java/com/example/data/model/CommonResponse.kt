package com.example.data.model

data class CommonResponse<T>(
    val result: String,
    val data: T,
    val errorCode: String,
    val errorMessage: String,
)
