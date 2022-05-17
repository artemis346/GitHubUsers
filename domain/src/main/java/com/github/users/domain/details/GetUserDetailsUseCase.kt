package com.github.users.domain.details

import com.github.users.repository.userdetails.dto.UserDetailsDto
import kotlinx.coroutines.flow.Flow

interface GetUserDetailsUseCase {

    fun getSelectedUser(userId: String?): Flow<UserDetailsDto>
}