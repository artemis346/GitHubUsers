package com.github.users.domain.user_list

import com.github.users.repository.userlist.dto.UserItemDto
import kotlinx.coroutines.flow.Flow

interface UserListUseCase {

    fun getUserList() : Flow<List<UserItemDto>>
}