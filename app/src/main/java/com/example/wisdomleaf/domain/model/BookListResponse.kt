package com.example.wisdomleaf.domain.model

data class BookListResponse(val data: List<BookListResponseItem>)

data class BookListResponseItem(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String
)