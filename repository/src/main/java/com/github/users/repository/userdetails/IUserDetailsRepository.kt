package com.github.users.repository.userdetails

import com.github.users.repository.userdetails.dto.UserDetailsDto
import kotlinx.coroutines.flow.Flow

interface IUserDetailsRepository {

    fun getSelectedUserDetails(userId: String?) : Flow<UserDetailsDto>
}