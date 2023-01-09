package com.example.wisdomleaf.data.datasource

import com.example.wisdomleaf.domain.datastate.DataState
import com.example.wisdomleaf.domain.model.BookListResponseItem
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getBookList(): Flow<DataState<List<BookListResponseItem>>>
}