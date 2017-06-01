package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.data.Movie
import com.example.pedroimai.kotlinrx2.data.MovieRestServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Pedro Imai on 30/05/2017.
 */
class MoviePresenter(var restService: MovieRestServiceApi, var view: MovieContract.View) : MovieContract.UserActions {

    override fun loadMovies() {
        val subscription =
                restService.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter {movie -> fakeValidation(movie)}
                .map{movie ->fakeMap(movie)}
                .subscribe(
                        { result -> view.showMovie(result) },
                        { pau -> android.util.Log.d("TESTE", pau.message) },
                        { android.util.Log.d("TESTE", "complete!") }
                )
    }

    override fun openMovieDetail(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun fakeValidation(param:Movie):Boolean{
        return param.year < 2000
    }

    fun fakeMap(param:Movie):Movie{
        param.title += " - Uau!"
        param.genre += " - Nuss!"

        return param
    }

}