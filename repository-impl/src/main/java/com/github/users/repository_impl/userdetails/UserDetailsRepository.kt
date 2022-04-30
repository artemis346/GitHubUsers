package com.github.users.repository_impl.userdetails

import com.github.users.datasource.ICacheDataSource
import com.github.users.datasource.SELECTED_USER_NAME
import com.github.users.network.api.users.UserApi
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.repository_impl.userdetails.mappers.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserDetailsRepository(
    private val api: UserApi,
    private val cache: ICacheDataSource
) : IUserDetailsRepository {

    override fun getSelectedUserDetails(): Flow<UserDetailsDto> {
        return flow {
            val userKey = cache.get(SELECTED_USER_NAME) as String?
            return@flow userKey?.let {
                val response = api.getUserDetails(userKey)
                emit(response.mapToDomain())
            } ?: error("No user to show")
        }
    }
}