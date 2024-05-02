package com.example.presentation.main.board

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.map
import com.example.domain.model.Board
import com.example.domain.model.Comment
import com.example.domain.usecase.main.board.DeleteBoardUseCase
import com.example.domain.usecase.main.board.GetBoardUseCase
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
    private val getBoardUseCase: GetBoardUseCase,
    private val deleteBoardUseCase: DeleteBoardUseCase,
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
            val boardFlow = getBoardUseCase().getOrThrow()
            val boardCardModelFlow =
                boardFlow.map { value: PagingData<Board> ->
                    value.map { board -> board.toUiModel() }
                }
            reduce {
                state.copy(boardCardModelFLow = boardCardModelFlow)
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
    
    fun onDeleteComment(comment: Comment) {
        
    }
}

data class BoardState(
    val boardCardModelFLow: Flow<PagingData<BoardCardModel>> = emptyFlow(),
    val deletedBoardIds: Set<Long> = emptySet(),
)

sealed interface BoardSideEffect {
    class Toast(val message: String) : BoardSideEffect
}
