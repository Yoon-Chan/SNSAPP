package com.example.presentation.model

import androidx.compose.runtime.Immutable
import com.example.domain.model.Board
import com.example.domain.model.Comment

@Immutable
data class BoardCardModel(
    val userId: Long,
    val boardId: Long,
    val images: List<String>,
    val username: String,
    val text: String,
    val comments: List<Comment>,
)

fun Board.toUiModel(): BoardCardModel {
    return BoardCardModel(
        userId = this.userId,
        boardId = this.id,
        username = this.username,
        images = this.images,
        text = this.content,
        comments = this.comments
    )
}
