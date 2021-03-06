package com.github.users.navigation

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object UserList : Screen("userList")
    object UserDetails : Screen("details/{userId}") {
        fun createRoute(userId: Any): String {
            return "details/$userId"
        }
    }
}