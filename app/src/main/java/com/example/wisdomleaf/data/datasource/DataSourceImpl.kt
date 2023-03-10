package com.example.wisdomleaf.data.datasource

import com.example.wisdomleaf.data.mapper.BookListResponseMapper
import com.example.wisdomleaf.domain.datastate.DataState
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.example.wisdomleaf.domain.retrofit.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataSourceImpl constructor(
    private val retrofitInterface: RetrofitHelper,
    private val bookListResponseMapper: BookListResponseMapper
) :
    DataSource {
    override suspend fun getBookList(): Flow<DataState<List<BookListResponseItem>>> = flow {
        retrofitInterface.getBooksList().collect {
            when (it) {
                is DataState.Success -> {
                    emit(DataState.Success(bookListResponseMapper.mapFromEntity(it.data)))
                }
                is DataState.Error ->{
                    emit(DataState.Error(it.e))
                }
                else -> {}
            }
        }
    }
}