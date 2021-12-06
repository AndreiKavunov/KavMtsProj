package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto



interface CategsDataSource {
	fun getMovies(): List<Categorie>
}