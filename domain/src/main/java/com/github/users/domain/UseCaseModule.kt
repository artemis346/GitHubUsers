package com.github.users.domain

import com.github.users.domain.details.GetUserDetailsUseCase
import com.github.users.domain.details.GetUserDetailsUseCaseImpl
import com.github.users.domain.setFavoriteUser.SetFavoriteUserUseCase
import com.github.users.domain.setFavoriteUser.SetFavoriteUserUseCaseImpl
import com.github.users.domain.user_list.UserListUseCase
import com.github.users.domain.user_list.UserListUseCaseImpl
import com.github.users.domain.userfilter.UserFilterUseCase
import com.github.users.domain.userfilter.UserFilterUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun provideGetUserDetailsUseCase(useCaseImpl: GetUserDetailsUseCaseImpl) : GetUserDetailsUseCase

    @Binds
    fun provideUserListUseCase(useCaseImpl: UserListUseCaseImpl) : UserListUseCase

    @Binds
    fun provideUserFilterUseCase(useCaseImpl: UserFilterUseCaseImpl) : UserFilterUseCase

    @Binds
    fun provideSetFavoriteUserUseCase(useCaseImpl: SetFavoriteUserUseCaseImpl) : SetFavoriteUserUseCase
}