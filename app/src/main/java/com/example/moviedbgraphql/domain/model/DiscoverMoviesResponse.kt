package com.example.moviedbgraphql.domain.model

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesResponse(
    @SerializedName("data")
    val `data`: Data? = Data()
) {
    data class Data(
        @SerializedName("discover_movies")
        val discoverMovies: DiscoverMovies? = DiscoverMovies()
    ) {
        data class DiscoverMovies(
            @SerializedName("page")
            val page: Int? = 0,
            @SerializedName("results")
            val results: List<Result?>? = listOf()
        ) {
            data class Result(
                @SerializedName("id")
                val id: Int? = 0,
                @SerializedName("overview")
                val overview: String? = "",
                @SerializedName("poster_path")
                val posterPath: String? = "",
                @SerializedName("title")
                val title: String? = ""
            )
        }
    }
}