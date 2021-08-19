package ru.kavunov.mtsproject.DTC

data class MovieDto(
    val id: String,
    val title: String,
    val backdrop_path: String,
    val description: String,
    val genre: String,
    val release_date: String,
    val rateScore: Float,
    val ageRestriction: String,
    val imageUrl: String,
    val actor: List<Actors>,



)
