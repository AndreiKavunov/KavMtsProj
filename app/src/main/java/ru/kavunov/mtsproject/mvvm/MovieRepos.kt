package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.*

class MovieRepos {
    companion object {

        var db: AppDatabase? = null

        var movieModel: MovieModel? = null
        var movieModelAll: List<MovieModel>? = null
        var profilTEST: List<MovListWithAct>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, id: Long, title: String, description: String, rateScore: Int,
                       ageRestriction: String,  imageUrl: String,) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = MovieModel(id, title, description, rateScore, ageRestriction, imageUrl)
                db!!.movieDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, id: Long) : MovieModel? {
            db = initializeDB(context)
            movieModel = db!!.movieDAO().getByName(id)
            return movieModel
        }

        fun getAll(context: Context) : List<MovieModel>? {
            db = initializeDB(context)
            movieModelAll = db!!.movieDAO().get()
            return movieModelAll
        }


            fun getTEST(context: Context) : List<MovListWithAct>? {
                db = ProfilRepos.initializeDB(context)
                profilTEST = db!!.movieDAO().getMovlistsWithAct()
                return profilTEST

        }

    }
}