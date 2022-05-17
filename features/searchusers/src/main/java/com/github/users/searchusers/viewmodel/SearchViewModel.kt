package com.github.users.searchusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.users.domain.search_users.FindUsersUseCase
import com.github.users.searchusers.mapper.mapPagingDomainToItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findUserUseCase: FindUsersUseCase
) : ViewModel() {

    companion object {
        const val SEARCH_DELAY = 100L
        const val MIN_QUERY_LENGTH = 1
    }

    private val stateData =
        MutableStateFlow<SearchUiState>(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState> = stateData

    private var query: String = ""

    fun startSearch(query: String) {
        this.query = query
        startSearch()
    }

    fun startSearch() {
        viewModelScope.launch {
            flowOf(query)
                .dropWhile {
                    it.length < MIN_QUERY_LENGTH
                }
                .map {
                    stateData.value = SearchUiState.Loading
                    delay(SEARCH_DELAY * 2)
                    it
                }
                .flatMapConcat {
                    findUserUseCase.findUsersByQuery(query = it)
                }
                .map { list ->
                    list.mapPagingDomainToItem()
                }
                .catch { ex ->
                    ex.printStackTrace()
                    stateData.value = SearchUiState.Error(ErrorState.ERROR_LOADING)
                }
                .collect { pagingData ->
                    stateData.value = SearchUiState.Success(flowOf(pagingData))
                }
        }
    }
}
