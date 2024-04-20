package com.example.data.model

import com.example.domain.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("loginId")
    val loginId: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("extraUserInfo")
    val extraUserInfo: String,
    @SerializedName("profileFilePath")
    val profileFilePath: String?,
) {
    fun toUser() = User(
        id = id,
        loginId = loginId,
        profileImageUrl = profileFilePath,
        username = userName
    )
}
