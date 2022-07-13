package com.github.users.repository.userlist

import com.github.users.repository.userlist.dto.UserItemDto
import kotlinx.coroutines.flow.Flow

interface IUserListRepository {

    fun getUserList(): Flow<List<UserItemDto>>

    fun getFavoriteUsersList(): Flow<List<UserItemDto>>
}