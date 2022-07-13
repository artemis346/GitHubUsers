package com.github.users.repository_impl.userlist.mapper


import com.github.user.database.entities.UserEntity
import com.github.users.network.api.userList.UserResponseItem
import com.github.users.repository.userlist.dto.UserItemDto

//
//fun List<UserResponseItem>.mapToDomain(): List<UserItemDto> {
//    if (isNullOrEmpty()) {
//        return listOf()
//    }
//
//    return map { item ->
//        UserItemDto(
//            id = item.id,
//            login = item.login,
//            avatarUrl = item.avatarUrl,
//            score = 0.0f
//        )
//    }
//}

fun List<UserResponseItem>.mapToEntity(): List<UserEntity> {
    return map {
        UserEntity(
            id = it.id,
            login = it.login,
            avatarUrl = it.avatarUrl
        )
    }
}

fun List<UserEntity>.mapToDomain(): List<UserItemDto> {
    if (isNullOrEmpty()) {
        return listOf()
    }

    return map { item ->
        UserItemDto(
            id = item.id,
            login = item.login,
            avatarUrl = item.avatarUrl,
            score = 0.0f
        )
    }
}