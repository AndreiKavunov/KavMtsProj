package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Categories
import ru.kavunov.mtsproject.DTC.MovieDto

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

class CategoryDataSourceImpl {
	 fun getMovies() = listOf(

		Categories(category = "боевики"),
		Categories(category = "драмы"),
		Categories(category = "комедии"),
		Categories(category = "артхаус"),
		Categories(category = "мелодрамы")

	)
}