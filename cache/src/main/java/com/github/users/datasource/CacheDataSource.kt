package com.github.users.datasource

import java.util.concurrent.ConcurrentHashMap

class CacheDataSource: ICacheDataSource {

    private val memoryCache = ConcurrentHashMap<String, Any>()

    override fun <T> get(key: String): T? {
        return memoryCache[key] as T?
    }

    override fun <T> put(key: String, value: T) {
        memoryCache[key] = Pair(key, value)
    }
}