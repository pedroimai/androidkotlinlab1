package com.example.pedroimai.kotlinrx2.movie

interface MovieCache {
    var movies: MutableList<Movie>
    infix fun store(newMovies: List<Movie>): List<Movie>
    val isEmpty: Boolean
}

class InMemoryMovieCache : MovieCache {
    override val isEmpty: Boolean
        get() = movies.isEmpty()

    override var movies: MutableList<Movie> = mutableListOf()

    override fun store(newMovies: List<Movie>): List<Movie> =
        movies.apply{
            clear()
            addAll(newMovies)
        }

}