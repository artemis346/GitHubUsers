package com.github.users.searchusers.di

import com.github.users.repository.search.ISearchRepository
import com.github.users.searchusers.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal class SearchModule {

    @Provides
    fun provideViewModel(repository: ISearchRepository) : SearchViewModel {
        return SearchViewModel(repository)
    }
}