package com.example.moviedbgraphql.domain.model

data class Fail(val throwable: Throwable) : Result<Nothing>