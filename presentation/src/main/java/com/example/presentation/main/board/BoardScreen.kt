package com.example.presentation.main.board

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.model.BoardCardModel
import com.example.presentation.ui.theme.SnsProjectTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun BoardScreen(
    viewModel : BoardViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    val items = state.boardCardModelFLow.collectAsLazyPagingItems()
    
    BoardScreen(
        boardCardModel = items,
        onOptionClick = {},
        onRelyClick = {}
    )
    
}

@Composable
fun BoardScreen(
    boardCardModel: LazyPagingItems<BoardCardModel>,
    onOptionClick: (BoardCardModel) -> Unit,
    onRelyClick: (BoardCardModel) -> Unit
) {
    Surface {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(boardCardModel.itemCount, key = {
                boardCardModel[it]?.boardId ?: it
            }) { index ->
                boardCardModel[index]?.run {
                    BoardCard(
                        username = username,
                        images = images,
                        text = text,
                        onOptionClick = {
                            onOptionClick(this)
                        },
                        onReplyClick = { onRelyClick(this) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BoardScreenPreview() {
    SnsProjectTheme {
//         BoardScreen(
//             boardCardModel = l,
//             onOptionClick = {},
//             onRelyClick = {}
//         )
    }
}
