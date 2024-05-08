package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.Board
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

@Entity
data class BoardDTO(
    @SerializedName("id")
    @PrimaryKey
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("createUserId")
    val createUserId: Long,
    @SerializedName("createUserName")
    val createUserName: String,
    @SerializedName("createUserProfileFilePath")
    val createUserProfileFilePath: String,
    @SerializedName("commentList")
    val commentList: List<CommentDTO>

) {
    
    fun toDomainModel(): Board {
        val contentParam = Gson().fromJson(this.content, ContentParam::class.java)
        return Board(
            id = this.id,
            title = this.title,
            username = this.createUserName,
            profileImageUrl = this.createUserProfileFilePath,
            content = contentParam.text,
            userId = this.createUserId,
            images = contentParam.images,
            comments = this.commentList.map { it.toDomainModel() }
        )
    }
}
