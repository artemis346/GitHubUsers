package com.github.users.repository_impl.di

import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository.userlist.IUserListRepository
import com.github.users.repository_impl.userdetails.UserDetailsRepository
import com.github.users.repository_impl.userlist.UserListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun provideUserDetailsRepository(repImpl: UserDetailsRepository): IUserDetailsRepository

    @Binds
    abstract fun provideUserListRepository(repImpl: UserListRepository): IUserListRepository
}