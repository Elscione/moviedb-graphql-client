package com.example.moviedbgraphql.di.component

import com.example.moviedbgraphql.data.dao.PersistedQueryDao
import com.example.moviedbgraphql.di.module.AppModule
import com.example.moviedbgraphql.di.module.RoomModule
import com.example.moviedbgraphql.domain.service.GraphqlService
import dagger.Component

@Component(modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    fun graphqlService(): GraphqlService
    fun persistedQueryDao(): PersistedQueryDao
}