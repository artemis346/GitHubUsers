package com.github.users.repository_impl.search.mappers

import com.github.users.network.api.search.SearchResponse
import com.github.users.repository.search.dto.SearchItemDto

fun SearchResponse.mapToDomain() : List<SearchItemDto> {
    if (items.isNullOrEmpty()) {
        return listOf()
    }

    return items.map { item ->
        SearchItemDto(
            id = item.id,
            login = item.login,
            avatarUrl = item.avatarUrl
        )
    }
}