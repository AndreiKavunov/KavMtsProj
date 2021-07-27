package ru.kavunov.mtsproject

import ru.kavunov.mtsproject.DTC.MovieDto

interface MovieClickListener {
    fun clickToach(a: MovieDto)
}