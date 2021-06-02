package com.example.moviedbgraphql.presentation.model

data class MovieUiModel(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val title: String
)