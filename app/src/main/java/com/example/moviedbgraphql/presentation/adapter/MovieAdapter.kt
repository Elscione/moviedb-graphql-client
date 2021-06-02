package com.example.moviedbgraphql.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbgraphql.R
import com.example.moviedbgraphql.presentation.model.MovieUiModel

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieUiModels: MutableList<MovieUiModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return movieUiModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        movieUiModels.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    fun setMovies(movies: List<MovieUiModel>) {
        movieUiModels.clear()
        movieUiModels.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivMoviePoster = itemView.findViewById<ImageView>(R.id.ivMoviePoster)
        private val tvMovieTitle = itemView.findViewById<TextView>(R.id.tvMovieTitle)
        private val tvMovieSynopsis = itemView.findViewById<TextView>(R.id.tvMovieSynopsis)

        fun bind(movieUiModel: MovieUiModel) {
            with(itemView) {
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + movieUiModel.posterPath)
                    .into(ivMoviePoster)
                tvMovieTitle.text = movieUiModel.title
                tvMovieSynopsis.text = movieUiModel.overview
            }
        }
    }
}