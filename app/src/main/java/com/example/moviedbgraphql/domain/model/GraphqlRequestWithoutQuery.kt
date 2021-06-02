package com.example.moviedbgraphql.domain.model

import com.google.gson.annotations.SerializedName

class GraphqlRequestWithoutQuery(
    @SerializedName("variables")
    private val variables: Map<String, Any>,
    @SerializedName("extensions")
    private val extensions: Extensions
)