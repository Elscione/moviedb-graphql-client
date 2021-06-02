package com.example.moviedbgraphql.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedbgraphql.data.dao.PersistedQueryDao
import com.example.moviedbgraphql.data.model.PersistedQuery

@Database(entities = [PersistedQuery::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun persistedQueryDao(): PersistedQueryDao
}