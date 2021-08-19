package ru.kavunov.mtsproject.recponse.respModel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.CategResp

class MovieRecpModel {
    companion object {
        suspend fun getAll() : List<MovieResponse>? = withContext(Dispatchers.IO){
            var movies: List<MovieResponse>
            try {
                movies = withContext(Dispatchers.IO) {
                    App.instance.apiService.getMovie().results
                }
               } catch (e: Exception) {
                movies = ArrayList()
            }
            return@withContext movies
        }


    }
}