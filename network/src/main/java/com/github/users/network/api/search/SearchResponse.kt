package com.github.users.network.api.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("items")
    val items: List<Items>,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean
)

data class Items(

    @SerializedName("id")
    val id: Long,

    @SerializedName("login")
    val login: String,

    @SerializedName("avatar_url")
    val avatarUrl: String


)