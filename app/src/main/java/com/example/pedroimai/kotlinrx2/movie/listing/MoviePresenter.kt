package com.example.pedroimai.kotlinrx2.movie.listing

import android.util.Log
import com.example.pedroimai.kotlinrx2.movie.Movie
import com.example.pedroimai.kotlinrx2.movie.MovieListingContract

class MoviePresenter(private val view: MovieListingContract.View,
                     private val source: MovieListingContract.Source

) : MovieListingContract.Presenter {
    override fun bind() {
        loadMovies()
    }

    private fun loadMovies() {
        source.getMovies(
                { movies -> view.showMovies(movies) },
                { exception -> Log.e("Erro", exception?.message, exception) }
        )
    }

    override fun openMovieDetail(movie: Movie) {
        source select movie
    }

    override fun unbind() {
    }

}