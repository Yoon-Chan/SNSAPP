package com.example.presentation.main.board

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.presentation.model.BoardCardModel
import com.example.presentation.ui.theme.SnsProjectTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun BoardScreen(
    viewModel : BoardViewModel
) {
    val state = viewModel.collectAsState().value
    val items = state.boardCardModelFLow.collectAsLazyPagingItems()
    val context = LocalContext.current
    
    var modelForDialog by remember {
        mutableStateOf<BoardCardModel?>(null)
    }
    
    viewModel.collectSideEffect {sideEffect ->
        when(sideEffect) {
            is BoardSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
        }
    }
    
    BoardScreen(
        boardCardModel = items,
        onOptionClick = {
                        modelForDialog = it
        },
        deletedBoardIds = state.deletedBoardIds,
        onRelyClick = {}
    )
    BoardOptionDialog(
        boardCardModel = modelForDialog,
        onDismissRequest = { modelForDialog = null },
        onBoardDelete = viewModel::onBoardDelete
        )
}

@Composable
fun BoardScreen(
    boardCardModel: LazyPagingItems<BoardCardModel>,
    deletedBoardIds: Set<Long>,
    onOptionClick: (BoardCardModel) -> Unit,
    onRelyClick: (BoardCardModel) -> Unit
) {
    Surface {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(boardCardModel.itemCount, key = {
                boardCardModel[it]?.boardId ?: it
            }) { index ->
                boardCardModel[index]?.run {
                    if(!deletedBoardIds.contains(this.boardId)){
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
