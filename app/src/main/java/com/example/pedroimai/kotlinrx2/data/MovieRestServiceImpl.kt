package com.example.pedroimai.kotlinrx2.data

import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by Pedro Imai on 29/05/2017.
 */

class MovieRestServiceImpl() : MovieRestServiceApi {

    override fun getMovies(): Flowable<Movie> {
        val fakeMoviesList: MutableList<Movie> = mutableListOf(
                Movie("Filme 1", "Comedy", 1999),
                Movie("Filme 2", "Drama", 1995),
                Movie("Filme 3", "Horror", 2012)
        )

        return Flowable.fromIterable(fakeMoviesList)
    }
}