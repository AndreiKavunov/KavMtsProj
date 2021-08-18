package ru.kavunov.mtsproject.recponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategRespList(
@SerialName("genres") val genres: List<CategResp>
)


@Serializable
data class CategResp(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,

)