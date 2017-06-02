package com.example.pedroimai.kotlinrx2.movie

import android.util.Log
import com.example.pedroimai.kotlinrx2.data.Movie
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pedro Imai on 30/05/2017.
 */
class MoviePresenter(var restService: MovieRestServiceApi, var view: MovieContract.View) : MovieContract.UserActions {

    override fun loadMovies() {
        restService.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { movie -> fakeValidation(movie) }
                .map { movie -> fakeMap(movie) }
                .subscribe(
                        { result -> view.showMovie(result) },
                        { pau -> Log.d("TESTE", pau.message) },
                        { Log.d("TESTE", "complete!") }
                )
    }

    override fun openMovieDetail(movie: Movie) {
        view.showMovieDetail(movie)
    }

    fun fakeValidation(param: Movie): Boolean {
        return param.year < 2000
    }

    fun fakeMap(param: Movie): Movie {
        param.title += " - Uau!"
        param.genre += " - Nuss!"

        return param
    }

}