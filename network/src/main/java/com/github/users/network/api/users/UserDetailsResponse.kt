package com.github.users.network.api.users

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    val login: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("location")
    val location: String?,

    @SerializedName("avatar_url")
    val avatarUrl: String?,

    @SerializedName("public_repos")
    val publicRepos: Int,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("following")
    val following: Int,

    @SerializedName("company")
    val companyName: String?,

    @SerializedName("created_at")
    val createAt: String?
)