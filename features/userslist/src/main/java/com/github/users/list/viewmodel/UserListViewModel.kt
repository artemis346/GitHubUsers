package com.github.users.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.users.domain.user_list.UserListUseCase
import com.github.users.domain.userfilter.UserFilterUseCase
import com.github.users.list.mapper.mapToItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase,
    private val userFilterUseCase: UserFilterUseCase
) : ViewModel() {

    private val stateData =
        MutableStateFlow<UserListUiState>(UserListUiState.Initial)
    val uiState: StateFlow<UserListUiState> = stateData

    private val filterState =
        MutableStateFlow<UserListFilterState>(UserListFilterState.All)
    val uiFilterState: StateFlow<UserListFilterState> = filterState

    fun fetchUsers() {
        if (filterState.value == UserListFilterState.Favorite) {
            fetchFavorite(filterState.value.value)
        }

        if (stateData.value !is UserListUiState.Initial) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            userListUseCase.getUserList()
                .onStart {
                    stateData.value = UserListUiState.Loading
                }
                .map { list ->
                    list.map { it.mapToItem() }
                }
                .catch { error ->
                    error.printStackTrace()
                    stateData.value = UserListUiState.Error(error = ErrorState.ERROR_LOADING)
                }
                .collect { data ->
                    if (data.isEmpty()) {
                        stateData.value = UserListUiState.Error(ErrorState.ERROR_LOADING)
                    } else {
                        stateData.value = UserListUiState.Success(data)
                    }
                }
        }
    }

    fun onFilterChanged(filterFavorite: Boolean) {
        filterState.value = if (filterFavorite) {
            UserListFilterState.Favorite
        } else {
            UserListFilterState.All
        }
        fetchFavorite(filterFavorite)
    }

    private fun fetchFavorite(filterFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            userFilterUseCase.getFilteredUsersList(filterFavorite)
                .map { list ->
                    list.map { it.mapToItem() }
                }
                .catch { error ->
                    stateData.value = UserListUiState.Error(error = ErrorState.ERROR_LOADING)
                    error.printStackTrace()
                }
                .collect { data ->
                    if (data.isEmpty()) {
                        stateData.value = UserListUiState.Error(ErrorState.ERROR_EMPTY_LIST_FAVORITE)
                    } else {
                        stateData.value = UserListUiState.Success(data)
                    }
                }
        }
    }
}