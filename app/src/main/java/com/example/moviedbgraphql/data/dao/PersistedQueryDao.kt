package com.example.moviedbgraphql.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviedbgraphql.data.model.PersistedQuery

@Dao
interface PersistedQueryDao {
    @Query("SELECT * FROM persistedquery WHERE `query` = :query")
    fun find(query: String): PersistedQuery

    @Insert
    fun insert(persistedQuery: PersistedQuery)
}