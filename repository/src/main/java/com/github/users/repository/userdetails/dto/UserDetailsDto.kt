package com.github.users.repository.userdetails.dto

import java.util.*

data class UserDetailsDto(
    val id: Long,
    val login: String,
    val email: String?,
    val name: String?,
    val location: String?,
    val avatarUrl: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val companyName: String?,
    val createAt: Date? = null,
    var isUserInFavorite: Boolean = false
)