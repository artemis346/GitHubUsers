package com.github.users.repository_impl.userlist

import com.github.user.database.dao.UserDao
import com.github.user.database.entities.UserEntity
import com.github.users.network.api.userList.UserListApi
import com.github.users.repository.userlist.IUserListRepository
import com.github.users.repository.userlist.dto.UserItemDto
import com.github.users.repository_impl.userlist.mapper.mapToDomain
import com.github.users.repository_impl.userlist.mapper.mapToEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserListRepository @Inject constructor(
    private val api: UserListApi,
    private val dao: UserDao
) : IUserListRepository {

    override fun getUserList(): Flow<List<UserItemDto>> {
        return flow {
            emit(getUsersFromNetwork())
        }
            .map {
                dao.insertUserList(it)
                it
            }
            .catch {
                emit(getUsersFromCache())
            }
            .map {
                it.mapToDomain()
            }
            .flowOn(Dispatchers.IO)
    }

    override fun getFavoriteUsersList(): Flow<List<UserItemDto>> {
        return flowOf(dao.getFavoriteUsers().mapToDomain())
    }

    private fun getUsersFromCache(): List<UserEntity> {
        return dao.getUsers()
    }

    private suspend fun getUsersFromNetwork(): List<UserEntity> {
        return api.userList(PAGE_SIZE).mapToEntity()
    }

    companion object {
        private const val PAGE_SIZE = 50
    }
}