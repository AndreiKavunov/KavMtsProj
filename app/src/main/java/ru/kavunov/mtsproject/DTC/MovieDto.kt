package ru.kavunov.mtsproject.DTC

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class MovieDto(
    val id: String,
    val title: String,

    val description: String,
    val genre: String,
    val releaseDate: String,
    val rateScore: Float,
    val ageRestriction: String,
    val imageUrl: String,
    )
