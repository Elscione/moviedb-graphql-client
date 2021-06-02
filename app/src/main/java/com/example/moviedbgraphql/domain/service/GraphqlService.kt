package com.example.moviedbgraphql.domain.service

import com.example.moviedbgraphql.domain.model.GraphqlRequestWithQuery
import com.example.moviedbgraphql.domain.model.GraphqlRequestWithoutQuery
import com.example.moviedbgraphql.domain.model.GraphqlResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface GraphqlService {
    @POST("./")
    @Headers("Content-Type: application/json")
    suspend fun post(@Body request: GraphqlRequestWithQuery): GraphqlResponse

    @POST("./")
    @Headers("Content-Type: application/json")
    suspend fun post(@Body request: GraphqlRequestWithoutQuery): GraphqlResponse
}