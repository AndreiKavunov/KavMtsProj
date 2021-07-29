package ru.kavunov.mtsproject.DTC

data class MovieDto(
    val title: String,
    val description: String,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String,
    val actor: List<Actors>,


)
