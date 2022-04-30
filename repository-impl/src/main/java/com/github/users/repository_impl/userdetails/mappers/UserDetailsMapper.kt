package com.github.users.repository_impl.userdetails.mappers

import com.github.users.network.api.users.UserDetailsResponse
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.utils.date.serverDateFormat
import java.util.*

fun UserDetailsResponse.mapToDomain(): UserDetailsDto {
    return UserDetailsDto(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        publicRepos = publicRepos,
        followers = followers,
        following = following,
        companyName = companyName,
        createAt = serverDateFormat.parse(createAt)
    )
}