package ru.kavunov.mtsproject.recponse

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import ru.kavunov.mtsproject.AUTH_HEADER

import ru.kavunov.mtsproject.BASE_URL
import ru.kavunov.mtsproject.DTC.ObjectResponse


interface ApiService {

    @GET("movie")
    suspend fun getFilms(@Query("&sort_by") endpoint: String = "popularity.desc",
                         @Query("api_key") key: String = AUTH_HEADER,
                         @Query("language") language: String = "ru"): ObjectResponse

//    @GET("movie")
//    suspend fun getFilms(): ObjectResponse

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