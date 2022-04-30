package com.github.users.repository_impl.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.users.network.api.search.SearchApi
import com.github.users.repository.search.dto.SearchItemDto
import com.github.users.repository_impl.search.mappers.mapToDomain
import java.lang.Exception

class SearchPagingSource constructor(
    private val api: SearchApi,
    private var query: String
) : PagingSource<Int, SearchItemDto>() {

    companion object {
        const val OFFSET = 5
        const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, SearchItemDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(OFFSET)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchItemDto> {
        val loadPage = params.key ?: FIRST_PAGE
        return try {
            val response = api.search(
                query = query,
                pageSize = params.loadSize,
                page = loadPage
            )
            val list = response.mapToDomain()
            LoadResult.Page(
                data = list,
                prevKey = null,
                nextKey = if (response.incompleteResults) {
                    loadPage + 1
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}