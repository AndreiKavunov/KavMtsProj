package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.*

class MovieModel {
    companion object {

        var db: AppDatabase? = null

        var movieTable: MovieTable? = null
        var movieTableAll: List<MovieTable>? = null
        var profilActs: List<MovListWithAct>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }


        suspend fun insertData(context: Context, id: Long, title: String, description: String, rateScore: Float,

                       ageRestriction: String,  imageUrl: String, releaseDate: String, genre: String)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            val loginDetails = MovieTable(id, title, description, releaseDate, rateScore, ageRestriction, imageUrl, genre)


            db?.movieDAO()?.insert(loginDetails)

        }

        suspend fun getMovieID(context: Context, id: Long) : MovieTable? = withContext(Dispatchers.IO){
            db = initializeDB(context)
            movieTable = db?.movieDAO()?.getByName(id)
            return@withContext movieTable
        }

        suspend fun getAll(context: Context) : List<MovieTable>? = withContext(Dispatchers.IO) {

            db = initializeDB(context)
            movieTableAll = db?.movieDAO()?.get()
            return@withContext movieTableAll
        }
        suspend fun deleteAll(context: Context)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            db?.movieDAO()?.delete()

        }

        suspend fun getActorList(context: Context, id: Long) : List<MovListWithAct>?= withContext(Dispatchers.IO) {
                db = ProfilModel.initializeDB(context)
                profilActs = db?.movieDAO()?.getMovlistsWithAct(id)
                return@withContext profilActs

        }

    }
}