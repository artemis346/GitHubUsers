package com.github.users.domain.userfilter

import com.github.users.repository.userlist.dto.UserItemDto
import kotlinx.coroutines.flow.Flow

interface UserFilterUseCase {

    fun getFilteredUsersList(favoriteFilters: Boolean): Flow<List<UserItemDto>>
}