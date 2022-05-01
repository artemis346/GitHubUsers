package com.github.users.userdetails.dto

import java.util.*

class UserDetailItem(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val name: String?,
    val location: String?,
    val email: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
    val companyName: String?,
    val createAt: Date? = null
)