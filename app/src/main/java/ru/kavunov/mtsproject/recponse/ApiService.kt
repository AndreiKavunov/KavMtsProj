package ru.kavunov.mtsproject.recponse

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.kavunov.mtsproject.DTC.MovieRespList
import ru.kavunov.mtsproject.ListFilm


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

    @GET("movie/{idfilm}/credits")
    suspend fun getActor(
        @Path("idfilm") idfilm: String = ListFilm.idFilm,
        @Query("api_key") key: String = AUTH_HEADER,
        @Query("language") language: String = "ru"): ActorRespList


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

const val BASE_URL = "https://api.themoviedb.org/3/"
const val APPLICATION_JSON_TYPE = "application/json"
const val AUTH_HEADER = "b62341778732f78e2661370039f79b84"
const val IMG_HEADER = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/"