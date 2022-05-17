package com.github.users.domain

import com.github.users.domain.details.GetUserDetailsUseCase
import com.github.users.domain.details.GetUserDetailsUseCaseImpl
import com.github.users.domain.search_users.FindUsersUseCase
import com.github.users.domain.search_users.FindUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun provideGetUserDetailsUseCase(repImpl: GetUserDetailsUseCaseImpl) : GetUserDetailsUseCase

    @Binds
    fun provideFindUsersUseCase(repImpl: FindUsersUseCaseImpl) : FindUsersUseCase
}