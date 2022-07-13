package com.github.users.list

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.github.users.navigation.Screen
import com.github.users.userlist_api.UserListFeatureApi
import javax.inject.Inject


class UserListFeatureImpl @Inject constructor() : UserListFeatureApi {

    override var screen: Screen = Screen.UserList

    override fun addGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = screen.route
        ) {
            UserListScreen(navController = navController)
        }
    }
}
