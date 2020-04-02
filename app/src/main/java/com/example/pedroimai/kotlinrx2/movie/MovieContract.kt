package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.shared.BindablePresenter
import io.reactivex.Observable
import io.reactivex.Single

data class Movie(val title: String, val episodeNumber: Int, val releaseDate: String)

interface MovieListingContract {
    interface View{
        fun showMovies(movies: List<Movie>)
        fun showMovieDetail(movie: Movie)
    }

    interface Presenter : BindablePresenter {
        fun openMovieDetail(movie: Movie)
    }

    interface Source {
        val flowOfMovies: Single<List<Movie>>
        fun getMovies(onSuccess: (List<Movie>) -> Unit, onError: (Throwable?) -> Unit)
        infix fun select(movie: Movie)

    }
}

interface MovieDetailContract {
    interface View {
        fun showMovie(movie:Movie)
    }

    interface Presenter : BindablePresenter

    interface Source {
        val selectedMovie :Observable<Movie>
    }

}