package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Categorie

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

class CategoryDataSourceImpl {
	 fun getMovies() = listOf(

				Categorie(category = "боевики"),
		Categorie(category = "драмы"),
		Categorie(category = "комедии"),
		Categorie(category = "артхаус"),
		Categorie(category = "мелодрамы"),
		Categorie(category = "фэнтези"),
		Categorie(category = "фантастика"),
		Categorie(category = "вестерн"),
		Categorie(category = "ужасы"),
		Categorie(category = "криминал"),

	)
}