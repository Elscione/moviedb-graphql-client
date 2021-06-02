package com.example.moviedbgraphql.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PersistedQuery(
    @PrimaryKey
    val query: String,
    @ColumnInfo(name = "sha256Hash")
    val hash: String
)