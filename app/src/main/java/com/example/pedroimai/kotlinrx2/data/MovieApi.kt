package com.example.pedroimai.kotlinrx2.data

import io.reactivex.Flowable

/**
 * Created by Pedro Imai on 29/05/2017.
 */
interface MovieApi {
    fun getMovies(): Flowable<Movie>
}
