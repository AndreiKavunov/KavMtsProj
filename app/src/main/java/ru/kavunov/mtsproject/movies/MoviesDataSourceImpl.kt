package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

//class MoviesDataSourceImpl : MoviesDataSource {
//
//
//	override fun getMovies(): List<List<MovieDto>> {
////		Thread.sleep(2000)
//
//		val listFilm = listOf(
//			listOf(
//				MovieDto(
//					id = "1",
//					genre = "empty",
//					releaseDate = "empty",
//
//					title = "Гнев человеческий",
//					description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
//					rateScore = 3F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
//				),
//
//				MovieDto(
//					id = "1",
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Мортал Комбат",
//					description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
//					rateScore = 5F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
//
//
//
//				),
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Упс... Приплыли!",
//					description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
//					rateScore = 5F,
//					ageRestriction = "6",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "The Box",
//					description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
//					rateScore = 4F,
//					ageRestriction = "12",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Сага о Дэнни Эрнандесе",
//					description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
//					rateScore = 2F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Пчелка Майя",
//					description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
//					rateScore = 4F,
//					ageRestriction = "0",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Круэлла",
//					description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
//					rateScore = 4F,
//					ageRestriction = "12",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//
//					title = "Чёрная вдова",
//					description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
//					rateScore = 3F,
//					ageRestriction = "16",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
//
//				),
//			),
//
//
//
//			listOf(
//
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Чёрная вдова",
//					description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
//					rateScore = 3F,
//					ageRestriction = "16",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
//
//				),
//
//
//
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Упс... Приплыли!",
//					description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
//					rateScore = 5F,
//					ageRestriction = "6",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
//
//				),
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "The Box",
//					description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
//					rateScore = 4F,
//					ageRestriction = "12",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
//
//				),
//
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Сага о Дэнни Эрнандесе",
//					description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
//					rateScore = 2F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
//
//				),
//
//
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Гнев человеческий",
//					description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
//					rateScore = 3F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
//
//				),
//
//				MovieDto(
//					id = "1",
//
//					genre = "empty",
//					releaseDate = "empty",
//					title = "Мортал Комбат",
//					description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
//					rateScore = 5F,
//					ageRestriction = "18",
//					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
//
//				),
//
//
//				)
//		)
//		return listFilm
//	}
//
//}