package com.example.pedroimai.kotlinrx2.movie

import com.example.pedroimai.kotlinrx2.application.api.StarWarsApi
import io.reactivex.Flowable
import io.reactivex.Scheduler

class MovieRepository(private val api: StarWarsApi, private val ioScheduler: Scheduler) :
    MovieContract.Source {
    override val movies: Flowable<List<Movie>>
        get() =
            api.getMovies()
                .subscribeOn(ioScheduler)
                .filter { it.isValid() }
                .map { it.body()?.results }
                .flatMap { moviePayload -> Flowable.fromIterable(moviePayload) }
                .filter { it.isValid() }
                .map { it.toMovie() }
                .toList()
                .toFlowable()

}


