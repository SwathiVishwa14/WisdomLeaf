package com.example.wisdomleaf.data.mapper

import com.example.wisdomleaf.data.model.BookListNetworkResponse
import com.example.wisdomleaf.data.model.BookListNetworkResponseItem
import com.example.wisdomleaf.domain.model.BookListResponse
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.example.wisdomleaf.utils.EntityMapper
import javax.inject.Inject

class BookListResponseMapper @Inject constructor() :
    EntityMapper<BookListNetworkResponse, BookListResponse> {

    override fun mapFromEntity(entity: BookListNetworkResponse): BookListResponse {
        return BookListResponse(data = entity.data.map { mapToData(it) })
    }

    fun mapToData(entity: BookListNetworkResponseItem): BookListResponseItem {
        return BookListResponseItem(
            id = entity.id,
            author = entity.author,
            width = entity.width,
            height = entity.height,
            url = entity.url,
            download_url = entity.downloadUrl
        )
    }

    override fun mapToEntity(domainModel: BookListResponse): BookListNetworkResponse {
        TODO("Not yet implemented")
    }
}