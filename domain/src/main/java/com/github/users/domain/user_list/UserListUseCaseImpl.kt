package com.github.users.domain.user_list

import com.github.users.repository.userlist.IUserListRepository
import com.github.users.repository.userlist.dto.UserItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserListUseCaseImpl @Inject constructor(
    private val repository: IUserListRepository
): UserListUseCase {

    override fun getUserList(): Flow<List<UserItemDto>> {
        return repository.getUserList()
    }
}