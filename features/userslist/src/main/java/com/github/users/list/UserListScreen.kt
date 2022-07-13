package com.github.users.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.withCreated
import androidx.navigation.NavHostController
import com.github.users.list.dto.UserItem
import com.github.users.list.viewmodel.UserListFilterState
import com.github.users.list.viewmodel.UserListUiState
import com.github.users.list.viewmodel.UserListViewModel
import com.github.users.navigation.Screen
import com.github.users.uikit.emptystate.EmptyState
import com.github.users.uikit.error.ErrorView
import com.github.users.uikit.items.ItemList
import com.github.users.uikit.shimmer.ListShimmer


@Composable
fun UserListScreen(navController: NavHostController, vm: UserListViewModel = hiltViewModel()) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(vm, lifecycle) {
        lifecycle.withCreated {
            vm.fetchUsers()
        }
    }
    Scaffold(
        backgroundColor = colorResource(id = R.color.white),
        topBar = {
            ScreenHeader(vm.uiFilterState.collectAsState()) {
                vm.onFilterChanged(it)
            }
        },
        content = {
            UserListContent(
                state = vm.uiState.collectAsState(),
                navController
            ) { vm.fetchUsers() }
        }
    )
}

@Composable
fun ScreenHeader(
    isChecked: State<UserListFilterState>,
    onFilterChecked: (isCheked: Boolean) -> Unit
) {
    val currentValue = isChecked.value.value
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.user_list_screen_title))
        },
        elevation = 0.dp,
        backgroundColor = colorResource(id = R.color.white),
        contentColor = colorResource(id = R.color.black),
        actions = {
            IconToggleButton(checked = currentValue, onCheckedChange = { isChecked ->
                onFilterChecked(isChecked)
            }) {
                Icon(
                    imageVector = if (currentValue) {
                        Icons.Outlined.List
                    } else {
                        Icons.Filled.Star
                    },
                    contentDescription = "Favorite filter",
                    tint = colorResource(id = R.color.purple_500)
                )
            }
        }

    )
}

@Composable
fun UserListContent(
    state: State<UserListUiState>,
    navController: NavHostController,
    errorAction: () -> Unit
) {
    when (val value = state.value) {
        is UserListUiState.Success -> {
            ScreenList(value.users, navController)
        }
        is UserListUiState.Loading -> {
            ListShimmer()
        }
        is UserListUiState.Initial -> {
            EmptyState(
                stringResource(R.string.user_list_reload_user_list),
                R.drawable.ic_search_large
            )
        }
        is UserListUiState.Error -> {
            SearchErrorView(errorAction, stringResource(value.error.message))
        }
    }
}

@Composable
fun ScreenList(
    users: List<UserItem>,
    navController: NavHostController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(users.size) { index ->
            users[index].let { user ->
                ItemList(
                    title = user.userName,
                    image = user.avatarUrl ?: "",
                    onClick = {
                        navController.navigate(Screen.UserDetails.createRoute(user.userName))
                    })
            }
        }
    }

}

@Composable
fun SearchErrorView(errorAction: () -> Unit, message: String) {
    ErrorView(message, errorAction)
}