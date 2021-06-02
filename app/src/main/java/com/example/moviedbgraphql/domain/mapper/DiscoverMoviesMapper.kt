package com.example.moviedbgraphql.domain.mapper

import com.example.moviedbgraphql.domain.model.DiscoverMoviesResponse
import com.example.moviedbgraphql.presentation.model.MovieUiModel
import javax.inject.Inject

class DiscoverMoviesMapper @Inject constructor() {
    fun mapDomainDataToUiModel(response: DiscoverMoviesResponse.Data.DiscoverMovies?): List<MovieUiModel> {
        return response?.results?.map {
            MovieUiModel(
                id = it?.id ?: 0,
                overview = it?.overview.orEmpty(),
                posterPath = it?.posterPath.orEmpty(),
                title = it?.title.orEmpty()
            )
        } ?: emptyList()
    }
}