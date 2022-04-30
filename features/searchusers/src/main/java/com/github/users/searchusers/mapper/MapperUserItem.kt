package com.github.users.searchusers.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.github.users.repository.search.dto.SearchItemDto
import com.github.users.searchusers.dto.UserItem

fun PagingData<SearchItemDto>.mapPagingDomainToItem(): PagingData<UserItem> {
    return map { item ->
        item.mapToUserItem()
    }
}

fun SearchItemDto.mapToUserItem(): UserItem {
    return UserItem(
        userName = login,
        avatarUrl = avatarUrl,
        score = String.format("%.2f", score)
    )
}