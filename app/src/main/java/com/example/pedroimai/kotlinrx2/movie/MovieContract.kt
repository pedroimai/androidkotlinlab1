package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.shared.BindablePresenter
import io.reactivex.Flowable

interface MovieContract{
    interface View{
        fun showMovies(movies: List<Movie>)
        fun showMovieDetail(movie: Movie)
    }

    interface Presenter : BindablePresenter {
        fun openMovieDetail(movie: Movie)
    }

    interface Source {
        val flowOfMovies: Flowable<List<Movie>>
        fun getMovies(onSuccess: (List<Movie>) -> Unit, onError: (Throwable?) -> Unit)
    }
}