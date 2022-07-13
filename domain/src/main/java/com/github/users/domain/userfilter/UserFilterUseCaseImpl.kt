package com.github.users.domain.userfilter

import com.github.users.repository.userlist.IUserListRepository
import com.github.users.repository.userlist.dto.UserItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserFilterUseCaseImpl @Inject constructor(
    private val repository: IUserListRepository
): UserFilterUseCase {

    override fun getFilteredUsersList(favoriteFilters: Boolean): Flow<List<UserItemDto>> {
        return if(favoriteFilters) {
            repository.getFavoriteUsersList()
        } else {
            repository.getUserList()
        }
    }

}