package com.example.data.model

import com.google.gson.annotations.SerializedName

data class FileDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("filePath")
    val filePath:String
)
