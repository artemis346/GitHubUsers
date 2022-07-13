package com.github.users.network.api.userList

import retrofit2.http.GET
import retrofit2.http.Query

interface UserListApi {

    @GET("/users")
    suspend fun userList(
        @Query("per_page") pageSize: Int
    ): List<UserResponseItem>
}