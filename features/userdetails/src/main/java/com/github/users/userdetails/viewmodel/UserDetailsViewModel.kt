package com.github.users.userdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.users.domain.details.GetUserDetailsUseCase
import com.github.users.repository.userdetails.IUserDetailsRepository
import com.github.users.userdetails.mapper.mapToItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetails: GetUserDetailsUseCase
) : ViewModel() {

    private val stateData =
        MutableStateFlow<UserDetailsUiState>(UserDetailsUiState.Initial)
    val uiState: StateFlow<UserDetailsUiState> = stateData

    fun fetchUserDetails(userId: String?) {
        viewModelScope.launch {
            getUserDetails.getSelectedUser(userId)
                .onStart {
                    stateData.value = UserDetailsUiState.Loading
                }
                .map {
                    it.mapToItem()
                }
                .catch { ex ->
                    ex.printStackTrace()
                    stateData.value = UserDetailsUiState.Error(ErrorState.ERROR_LOADING)
                }
                .collect { details ->
                    stateData.value = UserDetailsUiState.Success(details)
                }
        }
    }
}