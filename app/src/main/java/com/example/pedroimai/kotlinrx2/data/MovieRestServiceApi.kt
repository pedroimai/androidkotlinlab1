package com.example.pedroimai.kotlinrx2.data

import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by Pedro Imai on 29/05/2017.
 */
interface MovieRestServiceApi{
    fun getMovies(): Flowable<Movie>
}
