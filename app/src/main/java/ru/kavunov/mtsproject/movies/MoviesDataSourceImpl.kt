package ru.mts.teta.summer.android.homework.list.data.features.movies

import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto

//import ru.mts.teta.summer.android.homework.list.data.dto.MovieDto

class MoviesDataSourceImpl : MoviesDataSource {


	override fun getMovies(): List<List<MovieDto>> {
//		Thread.sleep(2000)

		val listFilm = listOf(
			listOf(
				MovieDto(
					title = "Гнев человеческий",
					description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
					rateScore = 3,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
							name = "Jason Statham"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg",
							name = "Holt McCallany"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg",
							name = "Josh Hartnett"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/e5GWh54fUmbupb5DKsSNU5axEx2.jpg",
							name = "Rocci Williams"
						),
					)
				),

				MovieDto(
					title = "Мортал Комбат",
					description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
					rateScore = 5,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg",
							name = "Lewis Tan"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg",
							name = "Jessica McNamee"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg",
							name = "Josh Lawson"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sL0H5my3PAc208n3yu64TO3ug12.jpg",
							name = "Tadanobu Asano"
						),

						)
				),
				MovieDto(
					title = "Упс... Приплыли!",
					description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
					rateScore = 5,
					ageRestriction = 6,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg",
							name = "Tara Flynn"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg",
							name = "Ava Connolly"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZRP9IfehCSx5OeBQQDcVPvKYD0.jpg",
							name = "Mary Murray"
						),



						)
				),
				MovieDto(
					title = "The Box",
					description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
					rateScore = 4,
					ageRestriction = 12,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg",
							name = "Chanyeol"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg",
							name = "Jo Dal-hwan"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg",
							name = "Gaeko"
						),

					)
				),
				MovieDto(
					title = "Сага о Дэнни Эрнандесе",
					description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
					rateScore = 2,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg",
							name = "6ix9ine"
						),
					)
				),
				MovieDto(
					title = "Пчелка Майя",
					description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
					rateScore = 4,
					ageRestriction = 0,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aVfEldX1ksEMrx45yNBAf9MAIDZ.jpg",
							name = "Benson Jack Anthony"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/qCp0psD5qzguABpRxWmMuC04kcl.jpg",
							name = "Frances Berry"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8OpoYvO1QqBYRAp1LxxUIiRdQG0.jpg",
							name = "Christian Charisiou"
						),

						)
				),
				MovieDto(
					title = "Круэлла",
					description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды.",
					rateScore = 4,
					ageRestriction = 12,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/2hwXbPW2ffnXUe1Um0WXHG0cTwb.jpg",
							name = "Emma Stone"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xr8Ki3CIqweWWqS5q0kUYdiK6oQ.jpg",
							name = "Emma Thompson"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/4nEKEWJpaTHncCTv6zeP98V0qGI.jpg",
							name = "Joel Fry"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hV2oiKF2xlDMXtuq3Si1TA4b6DA.jpg",
							name = "Paul Walter Hauser"
						),
					)
				),
				MovieDto(

					title = "Чёрная вдова",
					description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
					rateScore = 3,
					ageRestriction = 16,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg",
							name = "Scarlett Johansson"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg",
							name = "Florence Pugh"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3QbFXeiUzXUVUrJ7fdiCn7A7ReW.jpg",
							name = "Rachel Weisz"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/chPekukMF5SNnW6b22NbYPqAStr.jpg",
							name = "David Harbour"
						),
					)
				),
			),



			listOf(

				MovieDto(
					title = "Чёрная вдова",
					description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
					rateScore = 3,
					ageRestriction = 16,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg",
					actor = listOf(

						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6NsMbJXRlDZuDzatN2akFdGuTvx.jpg",
							name = "Scarlett Johansson"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/75PvULemW8BvheSKtPMoBBsvPLh.jpg",
							name = "Florence Pugh"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/3QbFXeiUzXUVUrJ7fdiCn7A7ReW.jpg",
							name = "Rachel Weisz"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/chPekukMF5SNnW6b22NbYPqAStr.jpg",
							name = "David Harbour"
						),

					)
				),



				MovieDto(
					title = "Упс... Приплыли!",
					description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
					rateScore = 5,
					ageRestriction = 6,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/17gBs4aux2NcnMvf3DK5UKUFttn.jpg",
							name = "Tara Flynn"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/o8uE77C4wQHYHJW6En192kjxJGd.jpg",
							name = "Ava Connolly"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/1ZRP9IfehCSx5OeBQQDcVPvKYD0.jpg",
							name = "Mary Murray"
						),


						)
				),
				MovieDto(
					title = "The Box",
					description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
					rateScore = 4,
					ageRestriction = 12,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg",
							name = "Chanyeol"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/jpEPPXmVC3EDMqrDQDYyXEMYlah.jpg",
							name = "Jo Dal-hwan"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fDO7vJVRkZOOY1GtQMJzf4N136q.jpg",
							name = "Gaeko"
						),
					)
				),

				MovieDto(
					title = "Сага о Дэнни Эрнандесе",
					description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
					rateScore = 2,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg",
					actor = listOf(
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xAlvyeC9zLbygGMxmmyTHymwuZP.jpg",
							name = "6ix9ine"
						),
					)
				),


				MovieDto(
					title = "Гнев человеческий",
					description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
					rateScore = 3,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg",
					actor = listOf(

						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
							name = "Jason Statham"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/8NvOcP35qv5UHWEdpqAvQrKnQQz.jpg",
							name = "Holt McCallany"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/dCfu2EN7FjISACcjilaJu7evwEc.jpg",
							name = "Josh Hartnett"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/e5GWh54fUmbupb5DKsSNU5axEx2.jpg",
							name = "Rocci Williams"
						),
					)
				),

				MovieDto(
					title = "Мортал Комбат",
					description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
					rateScore = 5,
					ageRestriction = 18,
					imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg",
					actor = listOf(

						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lkW8gh20BuwzHecXqYH1eRVuWpb.jpg",
							name = "Lewis Tan"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/aAfaMEEqD8syHv5bLi5B3sccrM2.jpg",
							name = "Jessica McNamee"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/Am9vM77uZd9bGODugwmWtOfzx6E.jpg",
							name = "Josh Lawson"
						),
						Actors(
							img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/sL0H5my3PAc208n3yu64TO3ug12.jpg",
							name = "Tadanobu Asano"
						),


						)
				),


				)
		)
		return listFilm
	}

}