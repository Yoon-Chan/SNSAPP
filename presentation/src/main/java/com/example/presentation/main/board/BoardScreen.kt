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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.Comment
import com.example.presentation.model.BoardCardModel
import com.example.presentation.ui.theme.SnsProjectTheme
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun BoardScreen(viewModel: BoardViewModel) {
    val state = viewModel.collectAsState().value
    val items = state.boardCardModelFLow.collectAsLazyPagingItems()
    val context = LocalContext.current
    var modelForDialog by remember {
        mutableStateOf<BoardCardModel?>(null)
    }
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is BoardSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
        }
    }

    BoardScreen(
        userId = state.myUserId,
        boardCardModel = items,
        onOptionClick = {
            modelForDialog = it
        },
        addedComments= state.addedComments,
        deletedComment = state.deletedComment,
        deletedBoardIds = state.deletedBoardIds,
        onDeleteComment = viewModel::onDeleteComment,
        onCommentSend= viewModel::onCommentSend
    )
    BoardOptionDialog(
        boardCardModel = modelForDialog,
        onDismissRequest = { modelForDialog = null },
        onBoardDelete = viewModel::onBoardDelete,
    )
}

@Composable
fun BoardScreen(
    userId: Long,
    boardCardModel: LazyPagingItems<BoardCardModel>,
    deletedBoardIds: Set<Long>,
    addedComments: Map<Long, List<Comment>>,
    deletedComment: Map<Long, List<Comment>>,
    onOptionClick: (BoardCardModel) -> Unit,
    onDeleteComment: (Long ,Comment) -> Unit,
    onCommentSend: (Long, String) -> Unit
) {
    Surface {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(boardCardModel.itemCount, key = {
                boardCardModel[it]?.boardId ?: it
            }) { index ->
                boardCardModel[index]?.run {
                    if (!deletedBoardIds.contains(this.boardId)) {
                        BoardCard(
                            isMine= userId == this.userId,
                            boardId = boardId,
                            username = username,
                            images = images,
                            text = text,
                            commentList = comments + addedComments[boardId].orEmpty() - deletedComment[boardId].orEmpty().toSet(),
                            onOptionClick = {
                                onOptionClick(this)
                            },
                            onDeleteComment = onDeleteComment,
                            onCommentSend= onCommentSend
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
