package ru.kavunov.mtsproject.recponse

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kavunov.mtsproject.AUTH_HEADER

import ru.kavunov.mtsproject.BASE_URL
import ru.kavunov.mtsproject.DTC.MovieRespList


interface ApiService {

    @GET("discover/movie")
    suspend fun getMovie(@Query("&sort_by") endpoint: String = "popularity.desc",
                         @Query("api_key") key: String = AUTH_HEADER,
                         @Query("language") language: String = "ru"): MovieRespList

    @GET("genre/movie/list")
    suspend fun getCateg(
//        @Query("&sort_by") endpoint: String = "popularity.desc",
                         @Query("api_key") key: String = AUTH_HEADER,
                         @Query("language") language: String = "ru"): CategRespList
//@GET("https://api.themoviedb.org/3/genre/movie/list?api_key=b62341778732f78e2661370039f79b84&language=RU")
//suspend fun getCateg(): CategRespList

//    https://api.themoviedb.org/3/genre/movie/list?api_key=b62341778732f78e2661370039f79b84&language=RU"
//    https://api.themoviedb.org/3/discover/movie?&sort_by=popularity.desc&api_key=

    companion object {
        fun create(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addJsonConverter()
                .build()
                .create(ApiService::class.java)
        }
    }
}