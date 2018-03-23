package com.example.pedroimai.kotlinrx2.movie.detail

import android.util.Log
import com.example.pedroimai.kotlinrx2.movie.MovieDetailContract
import com.example.pedroimai.kotlinrx2.shared.plusAssign
import com.example.pedroimai.kotlinrx2.shared.uiScheduler
import io.reactivex.disposables.CompositeDisposable

class MovieDetailRxPresenter(
    private val view: MovieDetailContract.View,
    private val source: MovieDetailContract.Source
) : MovieDetailContract.Presenter {

    private val subscriptions: CompositeDisposable = CompositeDisposable()
    override fun bind() {
        subscriptions.clear()

        subscriptions += source.selectedMovie
            .observeOn(uiScheduler)
            .subscribe(
                { selectedMovie -> view.showMovie(selectedMovie) },
                { exception -> Log.d("TESTE", exception.message) },
                { Log.d("TESTE", "complete!") }
            )

    }

    override fun unbind() {
        subscriptions.clear()
    }

}