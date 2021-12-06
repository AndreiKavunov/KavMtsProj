package ru.kavunov.mtsproject.recponse

import com.google.gson.annotations.Expose
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ActorRespList(
    @SerialName("cast") val cast: List<ActorResp>
)


@Serializable
data class ActorResp(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val profilePath: String?


    )
