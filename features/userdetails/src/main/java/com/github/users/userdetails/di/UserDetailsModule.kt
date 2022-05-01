package com.github.users.userdetails.di

import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.userdetails.viewmodel.UserDetailsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class UserDetailsModule {

    @Provides
    fun provideViewModel(repository: IUserDetailsRepository) : UserDetailsViewModel {
        return UserDetailsViewModel(repository)
    }
}