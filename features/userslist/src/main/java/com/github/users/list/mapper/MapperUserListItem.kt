package com.github.users.list.mapper

import com.github.users.list.dto.UserItem
import com.github.users.repository.userlist.dto.UserItemDto

fun UserItemDto.mapToItem() : UserItem {
    return UserItem(
        userName = login,
        score = score.toString(),
        avatarUrl = avatarUrl
    )
}