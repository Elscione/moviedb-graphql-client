package com.example.moviedbgraphql.domain.model

import com.google.gson.annotations.SerializedName

data class GraphqlRequestWithQuery(
    @SerializedName("query")
    private val query: String,
    @SerializedName("variables")
    private val variables: Map<String, Any>,
    @SerializedName("extensions")
    private val extensions: Extensions
)