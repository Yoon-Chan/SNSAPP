package com.example.presentation.main.board

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.model.Board
import com.example.domain.model.Comment
import com.example.domain.usecase.main.board.DeleteBoardUseCase
import com.example.domain.usecase.main.board.DeleteCommentUseCase
import com.example.domain.usecase.main.board.GetBoardUseCase
import com.example.domain.usecase.main.board.PostCommentUseCase
import com.example.domain.usecase.main.setting.GetMyUserUseCase
import com.example.presentation.model.BoardCardModel
import com.example.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@HiltViewModel
class BoardViewModel
    @Inject
    constructor(
        private val getMyUserUseCase: GetMyUserUseCase,
        private val getBoardUseCase: GetBoardUseCase,
        private val deleteBoardUseCase: DeleteBoardUseCase,
        private val postCommentUseCase: PostCommentUseCase,
        private val deleteCommentUseCase: DeleteCommentUseCase,
    ) : ViewModel(), ContainerHost<BoardState, BoardSideEffect> {
        override val container: Container<BoardState, BoardSideEffect> =
            container(
                initialState = BoardState(),
                buildSettings = {
                    this.exceptionHandler =
                        CoroutineExceptionHandler { _, throwable ->
                            intent {
                                postSideEffect(BoardSideEffect.Toast(throwable.message ?: ""))
                            }
                        }
                },
            )

        init {
            load()
        }

        fun load() =
            intent {
                val myUser = getMyUserUseCase().getOrThrow()
                val boardFlow = getBoardUseCase().getOrThrow()
                val boardCardModelFlow =
                    boardFlow.map { value: PagingData<Board> ->
                        value.map { board -> board.toUiModel() }
                    }
                reduce {
                    state.copy(
                        myUserId = myUser.id,
                        boardCardModelFLow = boardCardModelFlow,
                    )
                }
            }

        fun onBoardDelete(boardCardModel: BoardCardModel) =
            intent {
                deleteBoardUseCase(boardCardModel.boardId).getOrThrow()
                reduce {
                    state.copy(
                        deletedBoardIds = state.deletedBoardIds + boardCardModel.boardId,
                    )
                }
            }

        fun onCommentSend(
            boardId: Long,
            text: String,
        ) = intent {
            val user = getMyUserUseCase().getOrThrow()
            val commentId = postCommentUseCase(boardId, text).getOrThrow()
            val comment =
                Comment(
                    username = user.username,
                    text = text,
                    profileImageUrl = user.profileImageUrl,
                    id = commentId,
                )
            val newAddedComments =
                state.addedComments +
                    Pair(
                        boardId,
                        state.addedComments[boardId].orEmpty() + comment,
                    )

            reduce {
                state.copy(
                    addedComments = newAddedComments,
                )
            }
        }

        fun onDeleteComment(
            boardId: Long,
            comment: Comment,
        ) = intent {
            val deletedCommentId = deleteCommentUseCase(boardId, comment.id).getOrThrow()

            val newDeletedComment =
                state.deletedComment +
                    Pair(
                        boardId,
                        state.deletedComment[boardId].orEmpty() + comment,
                    )

            reduce {
                state.copy(
                    deletedComment = newDeletedComment,
                )
            }
        }
    }

data class BoardState(
    val myUserId: Long = 1,
    val boardCardModelFLow: Flow<PagingData<BoardCardModel>> = emptyFlow(),
    val deletedBoardIds: Set<Long> = emptySet(),
    val addedComments: Map<Long, List<Comment>> = emptyMap(),
    val deletedComment: Map<Long, List<Comment>> = emptyMap(),
)

sealed interface BoardSideEffect {
    class Toast(val message: String) : BoardSideEffect
}
