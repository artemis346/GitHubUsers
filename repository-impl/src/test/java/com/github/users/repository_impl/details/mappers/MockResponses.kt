package com.github.users.repository_impl

import com.github.users.network.api.user.UserDetailsResponse
import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.utils.date.serverDateFormat

val responseDetailsValid = UserDetailsResponse(
    id = 1234L,
    login = "JakeWharton",
    name = "Jake Wharton",
    email = "",
    location = "San Francisco",
    avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
    publicRepos = 124,
    followers = 62619,
    following = 9,
    companyName = "@square",
    createAt = "2009-03-24T16:09:53Z"
)

val dtoValid = UserDetailsDto(
    id = 1234L,
    login = "JakeWharton",
    name = "Jake Wharton",
    email = "",
    location = "San Francisco",
    avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
    publicRepos = 124,
    followers = 62619,
    following = 9,
    companyName = "@square",
    createAt = serverDateFormat.parse("2009-03-24T16:09:53Z")
)

val responseDetailsInvalid = listOf(
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = "2009-03-24T16:09:53"
    ),
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = ""
    ),
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = "2009 03 24T16:09:53Z"
    ),
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = "2009-03-24"
    )
)

val dtoInvalid = listOf(
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = null
    ),
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = null
    ),
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = null
    ),
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = "Jake Wharton",
        email = "",
        location = "San Francisco",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        publicRepos = 124,
        followers = 62619,
        following = 9,
        companyName = "@square",
        createAt = null
    )
)


val responseDetailsEmptyValues = listOf(
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = "",
        email = "",
        location = "",
        avatarUrl = "",
        publicRepos = 0,
        followers = 0,
        following = 0,
        companyName = "",
        createAt = "2009-03-24T16:09:53Z"
    ),
    UserDetailsResponse(
        id = 1234L,
        login = "JakeWharton",
        name = null,
        email = null,
        location = null,
        avatarUrl = null,
        publicRepos = 0,
        followers = 0,
        following = 0,
        companyName = null,
        createAt = "2009-03-24T16:09:53Z"
    )
)

val dtoEmpty = listOf(
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = "",
        email = "",
        location = "",
        avatarUrl = "",
        publicRepos = 0,
        followers = 0,
        following = 0,
        companyName = "",
        createAt = serverDateFormat.parse("2009-03-24T16:09:53Z")
    ),
    UserDetailsDto(
        id = 1234L,
        login = "JakeWharton",
        name = null,
        email = null,
        location = null,
        avatarUrl = null,
        publicRepos = 0,
        followers = 0,
        following = 0,
        companyName = null,
        createAt = serverDateFormat.parse("2009-03-24T16:09:53Z")
    )
)

