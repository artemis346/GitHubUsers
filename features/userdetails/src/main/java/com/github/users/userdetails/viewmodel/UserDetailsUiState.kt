package com.github.users.userdetails.viewmodel

import androidx.annotation.StringRes
import com.github.users.userdetails.R
import com.github.users.userdetails.dto.UserDetailItem

sealed class UserDetailsUiState {
    class Success(val item: UserDetailItem): UserDetailsUiState()
    object Loading: UserDetailsUiState()
    object Initial: UserDetailsUiState()
    class Error(val error: ErrorState): UserDetailsUiState()
}

enum class ErrorState(@StringRes val message: Int) {
    ERROR_LOADING(R.string.error_content_try_again)
}