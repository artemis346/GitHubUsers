package com.github.users.searchusers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.users.repository.search.ISearchRepository
import com.github.users.searchusers.mapper.mapPagingDomainToItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ISearchRepository
) : ViewModel() {

    companion object {
        const val SEARCH_DEBOUNCE = 3000L
        const val SEARCH_DELAY = 100L
        const val MIN_QUERY_LENGTH = 1
    }

    private val stateData =
        MutableStateFlow<SearchUiState>(SearchUiState.Initial)
    val uiState: StateFlow<SearchUiState> = stateData

    @FlowPreview
    fun startSearchWithDelay(query: String) {
        viewModelScope.launch {
            flowOf(query)
                .onStart {
                    delay(SEARCH_DELAY)
                }
                .dropWhile {
                    it.length < MIN_QUERY_LENGTH
                }
                .debounce(SEARCH_DEBOUNCE)
                .collect { query ->
                    repository.getUsersByQuery(query)
                        .onStart {
                            stateData.value = SearchUiState.Loading
                            delay(SEARCH_DELAY*2)
                        }
                        .map { list ->
                            list.mapPagingDomainToItem()
                        }
                        .catch { ex ->
                            ex.printStackTrace()
                            stateData.value = SearchUiState.Error
                        }
                        .collect { pagingData ->
                            stateData.value = SearchUiState.Success(flowOf(pagingData))
                        }
                }
        }
    }

    fun selectItem(userName: String) {
        viewModelScope.launch {
            flow {
                repository.selectItem(userName)
                emit(Unit)
            }.flowOn(Dispatchers.IO)
                .collect {
                    stateData.value = SearchUiState.ItemSelected
                }
        }
    }
}