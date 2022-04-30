package com.github.users.searchusers.viewmodel

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.github.users.searchusers.R
import com.github.users.searchusers.dto.UserItem
import kotlinx.coroutines.flow.Flow

sealed class SearchUiState {
    data class Success(val users: Flow<PagingData<UserItem>>) : SearchUiState()
    data class Error(val error: ErrorState) : SearchUiState()
    object ItemSelected : SearchUiState()
    object Initial : SearchUiState()
    object Loading : SearchUiState()
}

enum class ErrorState(@StringRes val message: Int) {
    ERROR_LOADING(R.string.error_content_try_again)
}