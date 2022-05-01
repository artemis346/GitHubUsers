package com.github.user.userdetails.viewmodel

import com.github.users.repository.userdetails.dto.UserDetailsDto
import com.github.users.userdetails.dto.UserDetailItem
import com.github.users.utils.date.serverDateFormat

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

val itemValid = UserDetailItem(
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