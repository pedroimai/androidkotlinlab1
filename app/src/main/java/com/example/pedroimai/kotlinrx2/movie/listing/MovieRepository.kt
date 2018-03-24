package com.example.pedroimai.kotlinrx2.movie.listing

import com.example.pedroimai.kotlinrx2.application.api.StarWarsApi
import com.example.pedroimai.kotlinrx2.movie.*
import com.example.pedroimai.kotlinrx2.shared.ioScheduler
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val api: StarWarsApi,
                      private val bus: MovieBus,
                      private val cache: MovieCache
) : MovieListingContract.Source {
    override val flowOfMovies: Flowable<List<Movie>>
        get() =
            if(cache.isEmpty)
                api.getRxMovies()
                    .subscribeOn(ioScheduler)
                    .filter { it.isValid() }
                    .map { it.body()?.results }
                    .flatMap { moviePayload -> Flowable.fromIterable(moviePayload) }
                    .filter { it.isValid() }
                    .map { it.toMovie()}
                    .toList()
                    .map{movies -> cache store movies }
                    .toFlowable()
            else Flowable.just(cache.movies)


    override fun getMovies(onSuccess: (List<Movie>) -> Unit, onError: (Throwable?) -> Unit) {
        if(cache.isEmpty)
            api.getMovies().enqueue(object : Callback<MovieListPayload> {
                override fun onResponse(call: Call<MovieListPayload>?, response: Response<MovieListPayload>?) {
                        var movies = listOf<Movie>()
                        if (response?.body() != null) {
                            if (response.isSuccessful && response.body()?.isValid() == true)
                                movies = response.body()?.results?.filter { it.isValid() }?.map { it.toMovie() }?.toList() ?: listOf()

                            cache store movies
                            onSuccess.invoke(movies)
                        }

                }

                override fun onFailure(call: Call<MovieListPayload>?, t: Throwable?) {
                    onError.invoke(t)
                }
             })
        else onSuccess(cache.movies)
    }

    override fun select(movie: Movie) {
        bus.movie.onNext(movie)
    }

}


