package com.example.moviedbgraphql.domain.model

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesRequest(
    @SerializedName("page")
    private val page: Int
)
