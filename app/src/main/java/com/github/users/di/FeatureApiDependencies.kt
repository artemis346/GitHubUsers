package com.github.users.di

import com.github.userdetails_api.UserDetailsFeatureApi
import com.github.users.userlist_api.UserListFeatureApi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface FeatureApiDependencies {
    fun provideUserDetailsFeatureApi(): UserDetailsFeatureApi
    fun provideUserListFeatureApi(): UserListFeatureApi
}