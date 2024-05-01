package com.example.data.model

import com.example.domain.model.Board
import com.google.gson.annotations.SerializedName

data class BoardDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title : String,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt : String,
    @SerializedName("createUserId")
    val createUserId: Long,
    @SerializedName("createUserName")
    val createUserName: String,
    @SerializedName("createUserProfileFilePath")
    val createUserProfileFilePath: String,
) {
    
    fun toDomainModel() : Board = Board(
        id = this.id,
        title = this.title,
        username = this.createUserName,
        profileImageUrl = this.createUserProfileFilePath,
        content = this.content,
        userId = this.createUserId
    )
}
