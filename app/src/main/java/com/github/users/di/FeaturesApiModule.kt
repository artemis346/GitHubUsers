package com.github.users.di

import com.github.searchusers_api.SearchUsersFeatureApi
import com.github.userdetails_api.UserDetailsFeatureApi
import com.github.users.searchusers.SearchUsersFeatureImpl
import com.github.users.userdetails.UserDetailsFeatureImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface FeaturesApiModule {

    @Binds
    fun provideSearchFeatureApi(implSearch: SearchUsersFeatureImpl): SearchUsersFeatureApi

    @Binds
    fun provideUserDetailsFeatureApi(impl: UserDetailsFeatureImpl): UserDetailsFeatureApi
}