package com.example.data.usecase.main.board

import com.example.data.model.CommentParam
import com.example.data.retrofit.BoardService
import com.example.domain.usecase.main.board.PostCommentUseCase
import javax.inject.Inject

class PostCommentUseCaseImpl @Inject constructor(
    private val boardService: BoardService
): PostCommentUseCase {
    
    override suspend fun invoke(boardId: Long, text: String): Result<Long> = runCatching {
        val requestBody = CommentParam(text).toRequestBody()
        
        boardService.postComment(
            boardId,
            requestBody
        ).data
    }
}
