package com.github.users.searchusers

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.searchusers_api.SearchUsersFeatureApi
import com.github.users.navigation.Screen
import javax.inject.Inject

class SearchUsersFeatureImpl @Inject constructor() : SearchUsersFeatureApi {

    override var screen: Screen = Screen.Search

    override fun addGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = screen.route
        ) {
            SearchScreen(navController = navController)
        }
    }
}