package com.example.pedroimai.kotlinrx2.movie

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class MovieListPayload(
    @SerializedName("count") val count: Int? = 0,
    @SerializedName("next") val next: Int? = 0,
    @SerializedName("previous") val previous: Int? = 0,
    @SerializedName("results") val results: List<MoviePayload>? = mutableListOf()
) {
    fun isValid(): Boolean = results != null

}

fun Response<MovieListPayload>.isValid(): Boolean = isSuccessful && body()?.isValid() ?: false

data class MoviePayload(
    @SerializedName("title") val title: String? = "",
    @SerializedName("episode_id") val episodeNumber: Int? = 0,
    @SerializedName("opening_crawl") val openingCrawl: String? = "",
    @SerializedName("director") val director: String? = "",
    @SerializedName("producer") val producer: String? = "",
    @SerializedName("release_date") val releaseDate: String? = ""
) {
    fun isValid(): Boolean = title != null && episodeNumber != null && releaseDate != null

    fun toMovie(): Movie = Movie(title.orEmpty(), episodeNumber ?: 0, releaseDate.orEmpty())
}