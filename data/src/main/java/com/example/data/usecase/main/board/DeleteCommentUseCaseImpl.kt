package com.example.data.usecase.main.board

import com.example.data.retrofit.BoardService
import com.example.domain.usecase.main.board.DeleteCommentUseCase
import javax.inject.Inject

class DeleteCommentUseCaseImpl @Inject constructor(
    private val boardService: BoardService
) : DeleteCommentUseCase {
    
    override suspend fun invoke(boardId: Long, commentId: Long): Result<Long> = runCatching {
        boardService.deleteComment(boardId, commentId).data
    }
}
