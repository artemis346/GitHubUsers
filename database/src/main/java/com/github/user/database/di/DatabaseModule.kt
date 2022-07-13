package com.github.user.database.di

import android.content.Context
import androidx.room.Room
import com.github.user.database.UserDataBase
import com.github.user.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext appContext: Context): UserDataBase {
        return Room.databaseBuilder(appContext, UserDataBase::class.java, UserDataBase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUsersDao(dataBase: UserDataBase): UserDao = dataBase.userDao()

}