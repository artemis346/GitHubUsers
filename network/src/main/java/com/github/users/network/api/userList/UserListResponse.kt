package com.github.users.network.api.userList

import com.google.gson.annotations.SerializedName

data class UserResponseItem(
    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String?,
)