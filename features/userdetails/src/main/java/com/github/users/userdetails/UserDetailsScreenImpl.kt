package com.github.users.userdetails

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.userdetails_api.UserDetailsFeatureApi
import com.github.users.navigation.Screen
import javax.inject.Inject

class UserDetailsFeatureImpl @Inject constructor() : UserDetailsFeatureApi {

    val userId = "userId"

    override var screen: Screen = Screen.UserDetails

    override fun addGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController,
        modifier: Modifier
    ) {
        navGraphBuilder.composable(
            route = screen.route,
            arguments = listOf(navArgument(userId) { type = NavType.StringType })
        ) {
            UserDetailScreen(navController = navController, it.arguments?.getString(userId))
        }
    }
}