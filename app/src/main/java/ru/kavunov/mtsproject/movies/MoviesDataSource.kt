package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.MovieDto



interface MoviesDataSource {

	fun getMovies(): List<List<MovieDto>>

}