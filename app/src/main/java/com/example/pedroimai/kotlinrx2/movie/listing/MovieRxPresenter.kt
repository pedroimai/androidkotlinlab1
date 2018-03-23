package com.example.pedroimai.kotlinrx2.movie.listing

import android.util.Log
import com.example.pedroimai.kotlinrx2.movie.Movie
import com.example.pedroimai.kotlinrx2.movie.MovieListingContract
import com.example.pedroimai.kotlinrx2.shared.plusAssign
import com.example.pedroimai.kotlinrx2.shared.uiScheduler
import io.reactivex.disposables.CompositeDisposable

class MovieRxPresenter(
    private val view: MovieListingContract.View,
    private val source: MovieListingContract.Source
) : MovieListingContract.Presenter {
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
        source select movie
    }

    override fun unbind() {
        subscriptions.clear()
    }

}