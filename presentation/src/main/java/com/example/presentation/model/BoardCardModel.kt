package com.example.presentation.model

import androidx.compose.runtime.Immutable
import com.example.domain.model.Board

@Immutable
data class BoardCardModel(
    val boardId: Long,
    val images: List<String>,
    val username: String,
    val text: String
)

fun Board.toUiModel() : BoardCardModel {
    return BoardCardModel(
        boardId = this.id,
        username = this.username,
        images = this.images,
        text = this.content
    )
}
