package com.github.users.datasource.di

import com.github.users.datasource.CacheDataSource
import com.github.users.datasource.ICacheDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun provideCacheDataSource(impl: CacheDataSource) : ICacheDataSource
}