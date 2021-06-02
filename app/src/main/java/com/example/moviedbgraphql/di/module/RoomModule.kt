package com.example.moviedbgraphql.di.module

import android.app.Application
import androidx.room.Room
import com.example.moviedbgraphql.data.dao.PersistedQueryDao
import com.example.moviedbgraphql.data.database.AppDatabase
import com.example.moviedbgraphql.data.repository.PersistedQueryRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    private val database: AppDatabase

    constructor(application: Application) {
        database = Room.databaseBuilder(application, AppDatabase::class.java, "app-db").build()
    }

    @Provides
    fun provideAppDatabase(): AppDatabase = database

    @Provides
    fun providePersistedQueryDao(database: AppDatabase): PersistedQueryDao =
        database.persistedQueryDao()

    @Provides
    fun providePersistedQueryRepository(persistedQueryDao: PersistedQueryDao): PersistedQueryRepository =
        PersistedQueryRepository(persistedQueryDao)
}