package com.github.users.repository_impl.userdetails.mappers

import com.github.users.network.api.users.UserDetailsResponse
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.utils.date.serverDateFormat
import java.lang.Exception
import java.util.*

fun UserDetailsResponse.mapToDomain(): UserDetailsDto {
    return UserDetailsDto(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        publicRepos = publicRepos,
        email = email,
        name = name,
        location = location,
        followers = followers,
        following = following,
        companyName = companyName,
        createAt = mapDate(createAt)
    )
}

internal fun mapDate(date: String?) : Date? {
    if (date.isNullOrEmpty()) {
        return null
    }

    return try {
        serverDateFormat.parse(date)
    } catch (ex: Exception) {
        null
    }
}