package com.github.users.list.viewmodel

import androidx.annotation.StringRes
import androidx.paging.PagingData
import com.github.users.list.R
import com.github.users.list.dto.UserItem
import kotlinx.coroutines.flow.Flow

sealed class UserListUiState {
    data class Error(val error: ErrorState): UserListUiState()
    data class Success(val users: List<UserItem>): UserListUiState()
    object Initial: UserListUiState()
    object Loading: UserListUiState()
}

enum class ErrorState(@StringRes val message: Int) {
    ERROR_LOADING(R.string.error_content_try_again),
    ERROR_EMPTY_LIST_FAVORITE(R.string.error_content_empty_favorite_list)
}