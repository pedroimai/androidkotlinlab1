package com.example.pedroimai.kotlinrx2.movie

import android.util.Log

class MoviePresenter(private val view: MovieContract.View,
                     private val source: MovieContract.Source

) : MovieContract.Presenter {
    override fun bind() {
        loadMovies()
    }

    private fun loadMovies() {
        source.getMovies(
            { movies -> view.showMovies(movies) },
            { exception -> Log.e("Erro", exception?.message) }
        )
    }

    override fun openMovieDetail(movie: Movie) {
        view.showMovieDetail(movie)
    }

    override fun unbind() {
    }

}