package com.github.user.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.user.database.entities.UserTable.COLUMN_AVATAR_URL
import com.github.user.database.entities.UserTable.COLUMN_IS_IN_FAVORITE
import com.github.user.database.entities.UserTable.COLUMN_LOGIN
import com.github.user.database.entities.UserTable.COLUMN_USER_ID
import com.github.user.database.entities.UserTable.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class UserEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_USER_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_LOGIN)
    val login: String,

    @ColumnInfo(name = COLUMN_AVATAR_URL)
    val avatarUrl: String?,

    @ColumnInfo(name = COLUMN_IS_IN_FAVORITE)
    val isInFavorite: Boolean = false
)