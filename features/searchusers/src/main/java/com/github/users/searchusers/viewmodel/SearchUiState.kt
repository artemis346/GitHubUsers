package com.github.users.searchusers.viewmodel

import androidx.paging.PagingData
import com.github.users.searchusers.dto.UserItem
import kotlinx.coroutines.flow.Flow

sealed class SearchUiState {
    data class Success(val users: Flow<PagingData<UserItem>>) : SearchUiState()
    object Error : SearchUiState()
    object ItemSelected : SearchUiState()
    object Initial : SearchUiState()
    object Loading : SearchUiState()
}