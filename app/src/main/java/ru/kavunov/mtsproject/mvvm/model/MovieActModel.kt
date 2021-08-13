package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.MovieActTable


class MovieActModel {
    companion object {

        var db: AppDatabase? = null

        var movActTableModel: MovieActTable? = null
        var movActTableModelAll: List<MovieActTable>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, idMo: Long, idAc: Long)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = MovieActTable(idMo, idAc)
                db?.movieActorDAO()?.insert(loginDetails)
            }
        }

        suspend fun getLoginDetails(context: Context, id: Long) : MovieActTable?= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            movActTableModel = db?.movieActorDAO()?.getByName(id)
            return@withContext movActTableModel
        }

        suspend fun getAll(context: Context) : List<MovieActTable>?= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            movActTableModelAll = db?.movieActorDAO()?.get()
            return@withContext movActTableModelAll
        }

    }
}