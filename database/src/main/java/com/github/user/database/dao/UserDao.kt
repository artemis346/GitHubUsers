package com.github.user.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.user.database.entities.UserEntity
import com.github.user.database.entities.UserTable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserList(flights: List<UserEntity>)

    @Query("SELECT * FROM ${UserTable.TABLE_NAME}")
    fun getUsers(): List<UserEntity>

    @Query("SELECT * FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.COLUMN_USER_ID} = :userId")
    fun getUsersById(userId: Long): UserEntity

    @Query("SELECT * FROM ${UserTable.TABLE_NAME} WHERE ${UserTable.COLUMN_IS_IN_FAVORITE} LIKE '1'")
    fun getFavoriteUsers(): List<UserEntity>

    @Query("UPDATE ${UserTable.TABLE_NAME} SET ${UserTable.COLUMN_IS_IN_FAVORITE} = :isInFavorite WHERE ${UserTable.COLUMN_LOGIN} = :userId")
    suspend fun updateUserInFavorite(userId: String?, isInFavorite: Boolean)
}