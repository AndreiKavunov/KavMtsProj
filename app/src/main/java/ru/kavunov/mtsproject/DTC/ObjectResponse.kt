package ru.kavunov.mtsproject.DTC

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Callback

@Serializable
data class ObjectResponse(
    @SerialName("results") val results: List<FilmResponse>
) {

}

@Serializable
data class FilmResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
//    @SerialName("overview") val overview: String,
//    val source: SourceResponse
)
