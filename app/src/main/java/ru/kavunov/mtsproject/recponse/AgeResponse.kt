package ru.kavunov.mtsproject.recponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AgeRespList(
    @SerialName("results") val results: List<AgeResp>
)


@Serializable
data class AgeResp(
    @SerialName("iso_3166_1") val iso31661: String,
    @SerialName("release_dates") val releaseDates: List<Release_dates>,

    )
@Serializable
data class Release_dates(
    @SerialName("certification") val certification: String,

    )
