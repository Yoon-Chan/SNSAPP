package com.example.data.model

import com.example.domain.model.Comment
import com.google.gson.annotations.SerializedName


data class CommentDTO(
    @SerializedName("id")
    val id: Long,
    @SerializedName("comment")
    val comment: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("createUserId")
    val createUserId: Long,
    @SerializedName("createUserName")
    val createUserName: String,
    @SerializedName("profileImageUrl")
    val profileImageUrl: String
)

fun CommentDTO.toDomainModel() : Comment {
    return Comment(
        id = id,
        profileImageUrl = profileImageUrl,
        text = comment,
        username = createUserName
    )
}
