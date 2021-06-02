package com.example.moviedbgraphql.presentation.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbgraphql.R
import com.example.moviedbgraphql.di.component.DaggerAppComponent
import com.example.moviedbgraphql.di.component.DaggerMainComponent
import com.example.moviedbgraphql.di.module.AppModule
import com.example.moviedbgraphql.di.module.RoomModule
import com.example.moviedbgraphql.domain.model.Fail
import com.example.moviedbgraphql.domain.model.Success
import com.example.moviedbgraphql.presentation.adapter.MovieAdapter
import com.example.moviedbgraphql.presentation.model.MovieUiModel
import com.example.moviedbgraphql.presentation.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    private var recyclerView: RecyclerView? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inject()
        setupViews()
        observeDiscoverMovies()
        viewModel.discoverMovies()
    }

    private fun observeDiscoverMovies() {
        viewModel.discoverMoviesResult.observe(this, { result ->
            when (result) {
                is Success -> onDiscoverMoviesSuccess(result.data)
                is Fail -> onDiscoverMoviesFailed(result.throwable)
            }
        })
    }

    private fun onDiscoverMoviesSuccess(data: List<MovieUiModel>) {
        adapter.setMovies(data)
    }

    private fun onDiscoverMoviesFailed(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.rvMovies)
        recyclerView?.adapter = adapter
    }

    private fun inject() {
        DaggerMainComponent.builder()
            .appComponent(
                DaggerAppComponent.builder()
                    .appModule(AppModule(application))
                    .roomModule(RoomModule(application))
                    .build()
            )
            .build()
            .inject(this)
    }
}