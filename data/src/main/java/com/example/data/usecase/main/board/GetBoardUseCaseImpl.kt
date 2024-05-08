package com.example.data.usecase.main.board

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.database.BoardDatabase
import com.example.data.database.BoardRemoteMediator
import com.example.domain.model.Board
import com.example.domain.usecase.main.board.GetBoardUseCase
import javax.inject.Inject
import javax.inject.Provider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetBoardUseCaseImpl @Inject constructor(
    private val boardDatabase: BoardDatabase,
    private val mediator: BoardRemoteMediator
) : GetBoardUseCase {
    
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(): Result<Flow<PagingData<Board>>> = runCatching {
        Pager(
            pagingSourceFactory = { boardDatabase.boardDao().getAll() },
            remoteMediator = mediator,
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
            )
        ).flow.map { paging ->
            paging.map { it.toDomainModel() }
        }
    }
    
    
}
