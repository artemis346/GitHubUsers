package com.github.users.repository_impl.userdetails

import com.github.users.network.api.users.UserApi
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.repository_impl.userdetails.mappers.mapToDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserDetailsRepository @Inject constructor(
    private val api: UserApi
) : IUserDetailsRepository {

    override fun getSelectedUserDetails(userId: String?): Flow<UserDetailsDto> {
        return flow {
            userId?.let {
                val response = api.getUserDetails(it)
                emit(response.mapToDomain())
            } ?: error("No user to show")
        }.flowOn(Dispatchers.IO)
    }
}