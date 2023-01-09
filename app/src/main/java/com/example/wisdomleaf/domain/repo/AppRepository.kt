package com.example.wisdomleaf.domain.repo

import com.example.wisdomleaf.data.datasource.DataSource
import com.example.wisdomleaf.domain.datastate.DataState
import com.example.wisdomleaf.domain.model.BookListResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppRepository constructor(
    private val bookListDataSource: DataSource
) {
    suspend fun getBookList(): Flow<DataState<List<BookListResponseItem>>> = flow {
        bookListDataSource.getBookList().collect {
            when (it) {
                is DataState.Success -> {
                    emit(DataState.Success(it.data))
                }
                is DataState.Loading -> {
                    emit(DataState.Loading(false))
                }
                is DataState.Error -> {
                    emit(DataState.Error(it.e))
                }
            }
        }
    }
}