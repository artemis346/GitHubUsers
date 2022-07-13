package com.github.users.network.api.user

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/users/{userName}")
    suspend fun getUserDetails(@Path("userName") userName: String): UserDetailsResponse
}