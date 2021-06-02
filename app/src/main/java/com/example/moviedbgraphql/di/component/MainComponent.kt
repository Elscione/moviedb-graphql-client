package com.example.moviedbgraphql.di.component

import com.example.moviedbgraphql.di.module.MainViewModelModule
import com.example.moviedbgraphql.presentation.activity.MainActivity
import dagger.Component

@Component(modules = [MainViewModelModule::class], dependencies = [AppComponent::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}