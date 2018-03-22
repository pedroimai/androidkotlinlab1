package com.example.pedroimai.kotlinrx2.movie

import android.util.Log
import com.example.pedroimai.kotlinrx2.data.Movie
import com.example.pedroimai.kotlinrx2.data.MovieApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MoviePresenter(private val restService: MovieApi,
                     private val view: MovieContract.View) : MovieContract.Presenter {
    override fun loadMovies() {
        restService.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { movie -> fakeValidation(movie) }
                .map { movie -> fakeMap(movie) }
                .subscribe(
                        { result ->
                            view.showMovie(result)
                            Log.d("TESTE", result.title)
                        },
                        { pau -> Log.d("TESTE", pau.message) },
                        { Log.d("TESTE", "complete!") }
                )

    }

    override fun openMovieDetail(movie: Movie) {
        view.showMovieDetail(movie)
    }

    private fun fakeValidation(param: Movie): Boolean {
        return param.year < 2000
    }

    private fun fakeMap(param: Movie): Movie {
        param.title += " - Uau!"
        param.genre += " - Nuss!"

        return param
    }

}