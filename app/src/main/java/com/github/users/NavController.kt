package com.github.users

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.github.users.searchusers.SearchScreen
import com.github.users.userdetails.UserDetailScreen


//TODO Add abstract navigation
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "search",

        ) {
        composable("search") {
            SearchScreen(navController)
        }
        composable(
            route = "details/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {
            UserDetailScreen(navController, it.arguments?.getString("userId"))
        }
    }
}