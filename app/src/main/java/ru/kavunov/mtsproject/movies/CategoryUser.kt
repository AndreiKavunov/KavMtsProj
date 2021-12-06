package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Categorie

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

class CategoryUser {
	 fun getMovies() = listOf(

		Categorie(category = "боевики"),
		Categorie(category = "драмы"),
		Categorie(category = "комедии")


	)
}