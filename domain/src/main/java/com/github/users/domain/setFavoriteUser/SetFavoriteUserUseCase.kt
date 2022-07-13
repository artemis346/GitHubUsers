package com.github.users.domain.setFavoriteUser

interface SetFavoriteUserUseCase {

    suspend fun setFavoriteUser(isInFavorite: Boolean)
}