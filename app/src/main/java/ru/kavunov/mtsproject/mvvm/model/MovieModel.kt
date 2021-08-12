package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.*

class MovieModel {
    companion object {

        var db: AppDatabase? = null

        var movieTableModel: MovieTableModel? = null
        var movieTableModelAll: List<MovieTableModel>? = null
        var profilTEST: List<MovListWithAct>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long, title: String, description: String, rateScore: Int,
                       ageRestriction: String,  imageUrl: String,)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            val loginDetails = MovieTableModel(id, title, description, rateScore, ageRestriction, imageUrl)
            db!!.movieDAO().insert(loginDetails)

        }

        suspend fun getMovieID(context: Context, id: Long) : MovieTableModel? = withContext(Dispatchers.IO){
            db = initializeDB(context)
            movieTableModel = db!!.movieDAO().getByName(id)
            return@withContext movieTableModel
        }

        suspend fun getAll(context: Context) : List<MovieTableModel>? = withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            db = initializeDB(context)
            movieTableModelAll = db!!.movieDAO().get()
            return@withContext movieTableModelAll
        }


        suspend fun getActorList(context: Context, id: Long) : List<MovListWithAct>?= withContext(Dispatchers.IO) {
                db = ProfilModel.initializeDB(context)
                profilTEST = db!!.movieDAO().getMovlistsWithAct(id)
                return@withContext profilTEST

        }

    }
}