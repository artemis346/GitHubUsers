package com.github.users.userdetails.mapper

import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.userdetails.dto.UserDetailItem

fun UserDetailsDto.mapToItem() : UserDetailItem {
    return UserDetailItem(
        id = id,
        login = login,
        email = email,
        location = location,
        name = name,
        avatarUrl = avatarUrl,
        publicRepos = publicRepos,
        followers = followers,
        following = following,
        companyName = companyName,
        createAt = createAt,
        isInFavorite = isUserInFavorite
    )
}