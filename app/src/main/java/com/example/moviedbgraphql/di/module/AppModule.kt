package com.example.moviedbgraphql.di.module

import android.app.Application
import com.example.moviedbgraphql.domain.GraphqlClient
import com.example.moviedbgraphql.domain.service.GraphqlService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Provides
    fun provideApplication() = application

    @Provides
    fun provideGraphqlService(): GraphqlService {
        return GraphqlClient.getInstance().create(GraphqlService::class.java)
    }
}