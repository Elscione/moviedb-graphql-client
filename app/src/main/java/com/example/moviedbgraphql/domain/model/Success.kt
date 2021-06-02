package com.example.moviedbgraphql.domain.model

data class Success<out T : Any>(val data: T) : Result<T>