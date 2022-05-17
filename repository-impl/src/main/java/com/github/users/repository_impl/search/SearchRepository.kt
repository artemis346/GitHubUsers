package com.github.users.repository_impl.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.users.network.api.search.SearchApi
import com.github.users.repository.search.ISearchRepository
import com.github.users.repository.search.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val api: SearchApi
) : ISearchRepository {

    companion object {
        const val PAGE_SIZE = 30
    }

    override fun getUsersByQuery(query: String): Flow<PagingData<SearchItemDto>> {
        return startSearch(query)
    }

    private fun startSearch(query: String): Flow<PagingData<SearchItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = api, query = query
                )
            }).flow
    }
}