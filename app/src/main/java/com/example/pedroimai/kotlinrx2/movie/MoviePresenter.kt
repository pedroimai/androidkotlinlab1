package com.example.pedroimai.kotlinrx2.movie

import android.util.Log
import com.example.pedroimai.kotlinrx2.shared.plusAssign
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class MoviePresenter(private val view: MovieContract.View,
                     private val uiScheduler: Scheduler,
                     private val source:MovieContract.Source,
                     private val subscriptions: CompositeDisposable = CompositeDisposable()
) : MovieContract.Presenter {
    override fun bind() {
        loadMovies()
    }


    private fun loadMovies() {
        subscriptions.clear()

        subscriptions += source.movies
                .observeOn(uiScheduler)
                .subscribe(
                        { result -> view.showMovies(result) },
                        { excpetion -> Log.d("TESTE", excpetion.message) },
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