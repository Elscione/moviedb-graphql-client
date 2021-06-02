package com.example.moviedbgraphql.domain.repository

import com.example.moviedbgraphql.data.repository.PersistedQueryRepository
import com.example.moviedbgraphql.domain.model.Extensions
import com.example.moviedbgraphql.domain.model.GraphqlRequestWithQuery
import com.example.moviedbgraphql.domain.model.GraphqlRequestWithoutQuery
import com.example.moviedbgraphql.domain.model.GraphqlResponse
import com.example.moviedbgraphql.domain.service.GraphqlService
import javax.inject.Inject

class GraphqlRepository @Inject constructor(
    private val persistedQueryRepository: PersistedQueryRepository,
    private val graphqlService: GraphqlService
) {
    companion object {
        private const val ERROR_CODE_PERSISTED_QUERY_NOT_FOUND = "PERSISTED_QUERY_NOT_FOUND"
    }

    private suspend fun sendWithQuery(
        query: String,
        hash: String,
        variables: Map<String, Any>
    ): GraphqlResponse {
        val request = GraphqlRequestWithQuery(
            query = query,
            variables = variables,
            extensions = Extensions(
                persistedQuery = Extensions.PersistedQuery(
                    version = 1,
                    hash = hash
                )
            )
        )
        return graphqlService.post(request)
    }

    private suspend fun sendWithoutQuery(
        hash: String,
        variables: Map<String, Any>
    ): GraphqlResponse {
        val request = GraphqlRequestWithoutQuery(
            variables = variables,
            extensions = Extensions(
                persistedQuery = Extensions.PersistedQuery(
                    version = 1,
                    hash = hash
                )
            )
        )
        return graphqlService.post(request)
    }

    suspend fun query(query: String, variables: Map<String, Any>): GraphqlResponse {
        val persistedQuery = persistedQueryRepository.getQueryHash(query)
        val hash = persistedQuery?.hash ?: persistedQueryRepository.persistQuery(query).hash
        val response = if (persistedQuery == null) {
            sendWithQuery(query, hash, variables)
        } else {
            sendWithoutQuery(hash, variables)
        }
        return if (response.errors?.firstOrNull()?.extensions?.code == ERROR_CODE_PERSISTED_QUERY_NOT_FOUND) {
            sendWithQuery(query, hash, variables)
        } else {
            response
        }
    }
}