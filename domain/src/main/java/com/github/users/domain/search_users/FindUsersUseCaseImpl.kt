package com.github.users.domain.search_users

import androidx.paging.PagingData
import com.github.users.repository.search.ISearchRepository
import com.github.users.repository.search.dto.SearchItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindUsersUseCaseImpl @Inject constructor(
    private val repository: ISearchRepository
) : FindUsersUseCase {

    override fun findUsersByQuery(query: String): Flow<PagingData<SearchItemDto>> {
        return repository.getUsersByQuery(query)
    }
}