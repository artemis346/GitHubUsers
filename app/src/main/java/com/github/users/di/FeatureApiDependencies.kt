package com.github.users.di

import com.github.searchusers_api.SearchUsersFeatureApi
import com.github.userdetails_api.UserDetailsFeatureApi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface FeatureApiDependencies {
    fun provideSearchUsersFeatureApi(): SearchUsersFeatureApi
    fun provideUserDetailsFeatureApi(): UserDetailsFeatureApi
}