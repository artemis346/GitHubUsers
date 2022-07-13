package com.github.user.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.user.database.UserDataBase.Companion.DB_CURRENT_VERSION
import com.github.user.database.dao.UserDao
import com.github.user.database.entities.UserEntity

@Database(
    version = DB_CURRENT_VERSION,
    entities = [
        UserEntity::class
    ]
)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DB_CURRENT_VERSION = 1
        const val DB_NAME = "users_db"
    }
}