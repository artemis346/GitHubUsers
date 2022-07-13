package com.github.users.domain.setFavoriteUser

import com.github.users.repository.userdetails.IUserDetailsRepository
import javax.inject.Inject

class SetFavoriteUserUseCaseImpl @Inject constructor(
    private val repository: IUserDetailsRepository
) : SetFavoriteUserUseCase {

    override suspend fun setFavoriteUser(isInFavorite: Boolean) {
        repository.updateUserInFavorite(isInFavorite)
    }
}