package com.github.users.repository.search

import androidx.paging.PagingData
import com.github.users.repository.search.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow

interface ISearchRepository {

    fun getUsersByQuery(query: String): Flow<PagingData<SearchItemDto>>

    fun selectItem(repId: String)
}