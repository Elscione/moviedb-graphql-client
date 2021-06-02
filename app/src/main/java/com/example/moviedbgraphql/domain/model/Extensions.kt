package com.example.moviedbgraphql.domain.model

import com.google.gson.annotations.SerializedName

data class Extensions(
    @SerializedName("persistedQuery")
    private val persistedQuery: PersistedQuery
) {
    data class PersistedQuery(
        @SerializedName("version")
        private val version: Int,
        @SerializedName("sha256Hash")
        private val hash: String
    )
}