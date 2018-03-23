package com.example.pedroimai.kotlinrx2.movie.detail

import com.example.pedroimai.kotlinrx2.movie.Movie
import com.example.pedroimai.kotlinrx2.movie.MovieBus
import com.example.pedroimai.kotlinrx2.movie.MovieDetailContract
import io.reactivex.Observable

class MovieDetailRepository(private val bus: MovieBus) :
    MovieDetailContract.Source {
    override val selectedMovie: Observable<Movie>
        get() = bus.movie

}