package com.example.pedroimai.kotlinrx2.movie

import android.util.Log
import com.example.pedroimai.kotlinrx2.shared.plusAssign
import com.example.pedroimai.kotlinrx2.shared.uiScheduler
import io.reactivex.disposables.CompositeDisposable

class MovieRxPresenter(
    private val view: MovieContract.View,
    private val source: MovieContract.Source
) : MovieContract.Presenter {
    private val subscriptions: CompositeDisposable = CompositeDisposable()
    override fun bind() {
        loadMovies()
    }


    private fun loadMovies() {
        subscriptions.clear()

        subscriptions += source.flowOfMovies
            .observeOn(uiScheduler)
            .subscribe(
                { result -> view.showMovies(result) },
                { exception -> Log.d("TESTE", exception.message) },
                { Log.d("TESTE", "complete!") }
            )

    }

    override fun openMovieDetail(movie: Movie) {
        view.showMovieDetail(movie)
    }

    override fun unbind() {
        subscriptions.clear()
    }

}