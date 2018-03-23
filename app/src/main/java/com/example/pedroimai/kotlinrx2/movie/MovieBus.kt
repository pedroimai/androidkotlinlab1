package com.example.pedroimai.kotlinrx2.movie

import io.reactivex.subjects.PublishSubject

interface MovieBus{
    val movie: PublishSubject<Movie>
}

class MovieRxBus: MovieBus{
    override val movie: PublishSubject<Movie> = PublishSubject.create()
}