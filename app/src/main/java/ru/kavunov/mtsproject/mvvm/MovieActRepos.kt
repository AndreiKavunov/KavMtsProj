package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.ActorModel
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.MovieAct


class MovieActRepos {
    companion object {

        var db: AppDatabase? = null

        var movActModel: MovieAct? = null
        var movActModelAll: List<MovieAct>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, idMo: Long, idAc: Long) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = MovieAct(idMo, idAc)
                db!!.movieActorDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, id: Long) : MovieAct? {
            db = initializeDB(context)
            movActModel = db!!.movieActorDAO().getByName(id)
            return movActModel
        }

        fun getAll(context: Context) : List<MovieAct>? {
            db = initializeDB(context)
            movActModelAll = db!!.movieActorDAO().get()
            return movActModelAll
        }

    }
}