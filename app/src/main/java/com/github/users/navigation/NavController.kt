package com.github.users.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.users.di.FeatureApiDependencies
import dagger.hilt.android.EntryPointAccessors

@Composable
fun NavigationComponent(activity: Activity, navController: NavHostController) {
    val depend = EntryPointAccessors.fromActivity(activity, FeatureApiDependencies::class.java)
    NavHost(
        navController = navController,
        startDestination = depend.provideUserListFeatureApi().screen.route,
    ) {
        register(
            depend.provideUserDetailsFeatureApi(),
            navController = navController
        )

        register(
            depend.provideUserListFeatureApi(),
            navController = navController
        )
    }
}



