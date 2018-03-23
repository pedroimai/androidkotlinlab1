package com.example.pedroimai.kotlinrx2.application.api

import com.example.pedroimai.kotlinrx2.movie.MovieListPayload
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

const val HEADER_ACCEPT = "Accept"
const val HEADER_ACCEPT_JSON = "application/json"
const val HEADER_CONTENT_JSON = "Content-Type: application/json"

interface StarWarsApi {
    @Headers(HEADER_CONTENT_JSON)
    @GET("films/")
    fun getRxMovies(): Flowable<Response<MovieListPayload>>

    @Headers(HEADER_CONTENT_JSON)
    @GET("films/")
    fun getMovies(): Call<MovieListPayload>
}