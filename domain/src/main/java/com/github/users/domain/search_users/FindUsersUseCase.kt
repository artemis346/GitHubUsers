package com.github.users.domain.search_users

import androidx.paging.PagingData
import com.github.users.repository.search.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow

interface FindUsersUseCase {

    fun findUsersByQuery(query: String): Flow<PagingData<SearchItemDto>>
}