package com.github.users.list.viewmodel

sealed class UserListFilterState(val value: Boolean) {
    object Favorite: UserListFilterState(true)
    object All : UserListFilterState(false)
}