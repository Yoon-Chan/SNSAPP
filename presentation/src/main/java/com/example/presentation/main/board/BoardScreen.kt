package com.example.presentation.main.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.presentation.model.BoardCardModel
import com.example.presentation.ui.theme.SnsProjectTheme

@Composable
fun BoardScreen(
    boardCardModel: List<BoardCardModel>,
    onOptionClick: (BoardCardModel) -> Unit,
    onRelyClick: (BoardCardModel) -> Unit
) {
    Surface {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(boardCardModel.size, key = {
                boardCardModel[it].boardId
            }) { index ->
                val boarCardModel = boardCardModel[index]
                BoardCard(
                    username = boarCardModel.username,
                    images = boarCardModel.images,
                    text = boarCardModel.text,
                    onOptionClick = {
                        onOptionClick(boarCardModel)
                    },
                    onReplyClick = { onRelyClick(boarCardModel) }
                )
            }
        }
    }
}

@Preview
@Composable
private fun BoardScreenPreview() {
    SnsProjectTheme {
        BoardScreen(
            boardCardModel = listOf(
                BoardCardModel(1, listOf(), "chan", text = "contents"),
                BoardCardModel(2, listOf(), "chan2", text = "contents2"),
                BoardCardModel(3, listOf(), "chan3", text = "contents3"),
            ),
            onOptionClick = {},
            onRelyClick = {}
        )
    }
}
