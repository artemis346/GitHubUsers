package com.github.users.datasource

interface ICacheDataSource {
    fun <T> put(key: String, value: T)
    fun <T> get(key: String) : T?
}