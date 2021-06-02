package com.example.moviedbgraphql.domain.usecase

import com.example.moviedbgraphql.domain.mapper.DiscoverMoviesMapper
import com.example.moviedbgraphql.domain.model.DiscoverMoviesRequest
import com.example.moviedbgraphql.domain.model.DiscoverMoviesResponse
import com.example.moviedbgraphql.domain.repository.GraphqlRepository
import com.example.moviedbgraphql.presentation.model.MovieUiModel
import javax.inject.Inject

class DiscoverMoviesUseCase @Inject constructor(
    private val graphqlRepository: GraphqlRepository,
    private val mapper: DiscoverMoviesMapper
) {

    companion object {
        private const val QUERY = """
            query(${'$'}input: DiscoverMoviesParams) {
                discover_movies(input: ${'$'}input) {
                    page
                    results {
                        id
                        title
                        poster_path
                        overview
                    }
                }
            }
        """
    }

    private fun createRequestParam(): Map<String, Any> {
        return mapOf(
            "input" to DiscoverMoviesRequest(page = 1)
        )
    }

    suspend fun execute(): List<MovieUiModel> {
        val result = graphqlRepository.query(QUERY, createRequestParam())
        if (result.errors.isNullOrEmpty()) {
            return mapper.mapDomainDataToUiModel(
                result.getData<DiscoverMoviesResponse.Data>(
                    DiscoverMoviesResponse.Data::class.java
                ).discoverMovies
            )
        } else {
            throw RuntimeException(result.errors.joinToString(", ") { it?.message.orEmpty() })
        }
    }
}