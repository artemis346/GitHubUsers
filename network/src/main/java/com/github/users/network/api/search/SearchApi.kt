package com.github.users.network.api.search

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search/users")
    suspend fun search(
        @Query("q") query: String,
        @Query("per_page") pageSize: Int,
        @Query("page") page: Int
    ): SearchResponse
}