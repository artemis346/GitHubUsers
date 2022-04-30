package com.github.users.network.api.users

import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/users/{userName}")
    fun getUserDetails(@Path("userName") userName: String): UserDetailsResponse
}