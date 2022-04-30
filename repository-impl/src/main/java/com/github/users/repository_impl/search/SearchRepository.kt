package com.github.users.repository_impl.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.users.datasource.ICacheDataSource
import com.github.users.datasource.SELECTED_USER_NAME
import com.github.users.network.api.search.SearchApi
import com.github.users.repository.search.ISearchRepository
import com.github.users.repository.search.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val api: SearchApi,
    private val cache: ICacheDataSource
) : ISearchRepository {

    companion object {
        const val PAGE_SIZE = 30
    }

    private var lastQuery = ""

    override fun getUsersByQuery(query: String): Flow<PagingData<SearchItemDto>> {
        lastQuery = query
        return startSearch()
    }

    override fun selectItem(repId: String) {
        cache.put(SELECTED_USER_NAME, repId)
    }

    private fun startSearch(): Flow<PagingData<SearchItemDto>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                SearchPagingSource(
                    api = api, query = lastQuery
                )
            }).flow
    }
}