package ru.kavunov.mtsproject

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSource

class MoviesModel(
	private val moviesDataSource: MoviesDataSource
) {
	
	fun getMovies() = moviesDataSource.getMovies()
}