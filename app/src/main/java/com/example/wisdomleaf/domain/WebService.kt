package com.example.wisdomleaf.domain

import com.example.wisdomleaf.data.model.BookListNetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("list?page=2&limit=20")
    suspend fun getBookList(): Response<BookListNetworkResponse>

}