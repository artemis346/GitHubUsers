package com.github.users.domain.details

import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository.userdetails.dto.UserDetailsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailsUseCaseImpl @Inject constructor(
    private val repository: IUserDetailsRepository
) : GetUserDetailsUseCase {

    override fun getSelectedUser(userId: String?): Flow<UserDetailsDto> {
        return repository.getSelectedUserDetails(userId)
    }


}