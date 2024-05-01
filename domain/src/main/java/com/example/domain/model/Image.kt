package com.example.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Image(
    @SerializedName("uri")
    val uri: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: Long,
    @SerializedName("mimeType")
    val mimeType: String,
): Serializable
