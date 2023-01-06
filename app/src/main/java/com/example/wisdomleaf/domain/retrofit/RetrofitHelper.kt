package com.example.wisdomleaf.domain.retrofit

import com.example.wisdomleaf.data.model.BookListNetworkResponse
import com.example.wisdomleaf.data.model.BookListNetworkResponseItem
import com.example.wisdomleaf.domain.datastate.DataState
import kotlinx.coroutines.flow.Flow

interface RetrofitHelper {
    suspend fun getBooksList(): Flow<DataState<BookListNetworkResponse>>
}