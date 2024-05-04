package com.example.presentation.model

import androidx.compose.runtime.Immutable
import com.example.domain.model.Board
import com.example.domain.model.Comment
import com.mohamedrejeb.richeditor.model.RichTextState

@Immutable
data class BoardCardModel(
    val userId: Long,
    val boardId: Long,
    val images: List<String>,
    val username: String,
    val richTextState: RichTextState,
    val comments: List<Comment>,
    val profileImageUrl: String? = null
)

fun Board.toUiModel(): BoardCardModel {
    return BoardCardModel(
        userId = this.userId,
        boardId = this.id,
        username = this.username,
        images = this.images,
        richTextState = RichTextState().apply { setHtml(this@toUiModel.content) },
        comments = this.comments,
        profileImageUrl = this.profileImageUrl
    )
}
