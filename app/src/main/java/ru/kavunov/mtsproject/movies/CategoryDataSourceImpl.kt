package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

class CategoryDataSourceImpl: CategsDataSource{
	 override fun getMovies(): List<Categorie>{

	val x = listOf(

		Categorie(category = "боевики"),
		Categorie(category = "драмы"),
		Categorie(category = "комедии"),
		Categorie(category = "артхаус"),
		Categorie(category = "мелодрамы"),
		Categorie(category = "фантастика"),
		Categorie(category = "фентези"),
		Categorie(category = "ужасы"),
		Categorie(category = "вестерн"),

	)
		 return x
}
}