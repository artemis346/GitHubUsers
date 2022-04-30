package com.github.users.searchusers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.users.searchusers.dto.UserItem
import com.github.users.searchusers.viewmodel.SearchUiState
import com.github.users.searchusers.viewmodel.SearchViewModel
import com.github.users.uikit.emptystate.EmptyState
import com.github.users.uikit.items.ItemList
import com.github.users.uikit.shimmer.ListShimmer
import com.github.users.uikit.textfield.SearchTextField
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchScreen(navController: NavHostController) {
    val vm: SearchViewModel = hiltViewModel()
    Scaffold(
        backgroundColor = colorResource(id = R.color.white),
        topBar = { ScreenHeader(vm = vm) },
        content = { ScreenContent(vm = vm, navController) }
    )
}

@Composable
fun ScreenHeader(vm: SearchViewModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SearchTextField(
                placeholder = R.string.search_placeholder,
                onValueChange = {
                    vm.startSearchWithDelay(it)
                })
        }
    }
}

@Composable
fun ScreenContent(vm: SearchViewModel, navController: NavHostController) {
    when (val state = vm.uiState.collectAsState().value) {
        is SearchUiState.Success -> {
            ScreenList(state.users, vm)
        }
        is SearchUiState.Loading -> {
            ListShimmer()
        }
        is SearchUiState.ItemSelected -> {
        //TODO repace with abstract navigation
            navController.navigate("details")
        }
        is SearchUiState.Initial -> {
            EmptyState(stringResource(R.string.start_new_search), R.drawable.ic_search_large)
        }
        is SearchUiState.Error -> {
        }
    }
}

@Composable
fun ScreenList(users: Flow<PagingData<UserItem>>, vm: SearchViewModel) {
    val items = users.collectAsLazyPagingItems()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items.itemCount) { index ->
            items[index]?.let { user ->
                ItemList(
                    title = user.userName,
                    image = user.avatarUrl,
                    subTitle = stringResource(id = R.string.search_score, user.score),
                    onClick = {
                        vm.selectItem(user.userName)
                    })
            }
        }
    }
}
