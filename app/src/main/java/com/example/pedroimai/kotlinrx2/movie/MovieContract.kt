package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.data.Movie

/**
 * Created by Pedro Imai on 30/05/2017.
 */
interface MovieContract{
    interface View{
        fun showMovie(movies:Movie)
    }

    interface UserActions{
        fun loadMovies()
    }
}