package com.github.users.repository_impl.userdetails

import com.github.user.database.dao.UserDao
import com.github.users.network.api.user.UserApi
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.repository_impl.userdetails.mappers.mapToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepository @Inject constructor(
    private val api: UserApi,
    private val dao: UserDao
) : IUserDetailsRepository {

    private var userId: String? = null

    override fun getSelectedUserDetails(userId: String?): Flow<UserDetailsDto> {
        this.userId = userId
        return flow {
            userId?.let {
                val response = api.getUserDetails(it)
                emit(response.mapToDomain())
            } ?: error("No user to show")
        }.map {
            it.apply {
                val userEntity = dao.getUsersById(it.id)
                isUserInFavorite = userEntity.isInFavorite
            }
        }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun updateUserInFavorite(isInFavorite: Boolean) {
        dao.updateUserInFavorite(userId = userId, isInFavorite = isInFavorite)
    }


}