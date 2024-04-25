package com.example.domain.model

import com.google.gson.annotations.SerializedName


data class Image(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: Long,
    @SerializedName("mimeType")
    val mimeType: String,
)
