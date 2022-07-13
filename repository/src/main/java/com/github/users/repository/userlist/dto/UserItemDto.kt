package com.github.users.repository.userlist.dto

data class UserItemDto(
    val id: Long,
    val login: String,
    val avatarUrl: String?,
    val score: Float
)