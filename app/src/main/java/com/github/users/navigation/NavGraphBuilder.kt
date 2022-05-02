package com.github.users.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.register(
    featureApi: FeatureApi,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    featureApi.addGraph(
        navGraphBuilder = this,
        navController = navController,
        modifier = modifier
    )
}