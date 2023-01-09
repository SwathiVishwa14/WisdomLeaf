package com.example.wisdomleaf.data.mapper

import com.example.wisdomleaf.data.model.BookListNetworkResponseItem
import com.example.wisdomleaf.domain.model.BookListResponseItem
import com.example.wisdomleaf.utils.EntityMapper
import javax.inject.Inject

class BookListResponseMapper @Inject constructor() :
    EntityMapper<List<BookListNetworkResponseItem>, List<BookListResponseItem>> {

    override fun mapFromEntity(entity: List<BookListNetworkResponseItem>): List<BookListResponseItem> {
       return entity.map { mapToData(it) }
    }
    private fun mapToData(entity: BookListNetworkResponseItem): BookListResponseItem {
        return BookListResponseItem(
            id = entity.id,
            author = entity.author,
            width = entity.width,
            height = entity.height,
            url = entity.url,
            download_url = entity.downloadUrl
        )
    }

    override fun mapToEntity(domainModel: List<BookListResponseItem>): List<BookListNetworkResponseItem> {
        TODO("Not yet implemented")
    }
}