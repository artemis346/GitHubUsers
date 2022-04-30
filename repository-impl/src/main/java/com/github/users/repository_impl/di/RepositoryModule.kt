package com.github.users.repository_impl.di

import com.github.users.repository.search.ISearchRepository
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.repository_impl.search.SearchRepository
import com.github.users.repository_impl.userdetails.UserDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun provideSearchRepository(repImpl: SearchRepository) : ISearchRepository

    @Binds
    abstract fun provideUserDetailsRepository(repImpl: UserDetailsRepository) : IUserDetailsRepository
}