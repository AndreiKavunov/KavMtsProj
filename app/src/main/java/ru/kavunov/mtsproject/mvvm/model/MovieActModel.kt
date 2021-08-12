package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.MovieActTableModel


class MovieActModel {
    companion object {

        var db: AppDatabase? = null

        var movActTableModelModel: MovieActTableModel? = null
        var movActTableModelModelAll: List<MovieActTableModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, idMo: Long, idAc: Long)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = MovieActTableModel(idMo, idAc)
                db!!.movieActorDAO().insert(loginDetails)
            }
        }

        suspend fun getLoginDetails(context: Context, id: Long) : MovieActTableModel?= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            movActTableModelModel = db!!.movieActorDAO().getByName(id)
            return@withContext movActTableModelModel
        }

        suspend fun getAll(context: Context) : List<MovieActTableModel>?= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            movActTableModelModelAll = db!!.movieActorDAO().get()
            return@withContext movActTableModelModelAll
        }

    }
}