package ru.kavunov.mtsproject.DTC

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieRespList(
    @SerialName("results") val results: List<MovieResponse>
) {

}

@Serializable
data class MovieResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
    @SerialName("genre_ids") val genreIds: List<Int>,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val voteAverage: Float,
    @SerialName("poster_path") val posterPath: String,


    )

