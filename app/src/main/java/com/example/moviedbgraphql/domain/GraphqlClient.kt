package com.example.moviedbgraphql.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GraphqlClient {
    private const val BASE_URL = "https://moviedb-graphql.herokuapp.com"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    private var retrofit: Retrofit? = null

    private fun createNewInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getInstance(): Retrofit {
        val retrofit = retrofit ?: createNewInstance()
        this.retrofit = retrofit
        return retrofit
    }
}