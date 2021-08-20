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
    @SerialName("genre_ids") val genre_ids: List<Int>,
    @SerialName("release_date") val release_date: String,
    @SerialName("vote_average") val vote_average: Float,
    @SerialName("poster_path") val poster_path: String,
//    @SerialName("backdrop_path") val backdrop_path: String,

)

