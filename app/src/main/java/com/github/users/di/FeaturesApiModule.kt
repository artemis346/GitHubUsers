package com.github.users.di

import com.github.userdetails_api.UserDetailsFeatureApi
import com.github.users.list.UserListFeatureImpl
import com.github.users.userdetails.UserDetailsFeatureImpl
import com.github.users.userlist_api.UserListFeatureApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface FeaturesApiModule {

    @Binds
    fun provideUserDetailsFeatureApi(impl: UserDetailsFeatureImpl): UserDetailsFeatureApi

    @Binds
    fun provideUserListFeatureApi(impl: UserListFeatureImpl): UserListFeatureApi
}