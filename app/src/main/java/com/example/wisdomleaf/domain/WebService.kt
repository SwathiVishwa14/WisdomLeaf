package com.example.wisdomleaf.domain

import com.example.wisdomleaf.data.model.BookListNetworkResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("list?page=1&limit=20")
    suspend fun getBookList(): Response<List<BookListNetworkResponseItem>>

}