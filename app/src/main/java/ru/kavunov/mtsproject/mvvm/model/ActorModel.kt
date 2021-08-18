package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.AppDatabase

class ActorModel {
    companion object {

        var db: AppDatabase? = null

        var actorTable: ActorTable? = null
        var actorTableAll: List<ActorTable>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long, imgAct: String, nameAct: String)  = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            val loginDetails = ActorTable(id, imgAct, nameAct)
            db?.actorDAO()?.insert(loginDetails)

        }

        suspend fun getLoginDetails(context: Context, id: Long) : ActorTable? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            actorTable = db?.actorDAO()?.getByName(id)
            return@withContext actorTable
        }

        suspend fun getAll(context: Context) : List<ActorTable>? = withContext(Dispatchers.IO){
            db = initializeDB(context)
            actorTableAll = db?.actorDAO()?.get()
            return@withContext actorTableAll
        }

    }
}