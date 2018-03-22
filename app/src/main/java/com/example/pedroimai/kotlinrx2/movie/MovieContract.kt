package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.data.Movie

interface MovieContract{
    interface View{
        fun showMovie(movies:Movie)
        fun showMovieDetail(movie:Movie)
    }

    interface Presenter {
        fun loadMovies()
        fun openMovieDetail(movie:Movie)
    }
}