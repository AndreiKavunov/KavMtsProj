package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.ActorTableModel
import ru.kavunov.mtsproject.bd.AppDatabase

class ActorModel {
    companion object {

        var db: AppDatabase? = null

        var actorTableModel: ActorTableModel? = null
        var actorTableModelAll: List<ActorTableModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long, imgAct: String, nameAct: String)  = withContext(Dispatchers.IO) {{
            db = initializeDB(context)
            val loginDetails = ActorTableModel(id, imgAct, nameAct)
            db!!.actorDAO().insert(loginDetails)
            }
        }

        suspend fun getLoginDetails(context: Context, id: Long) : ActorTableModel? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            actorTableModel = db!!.actorDAO().getByName(id)
            return@withContext actorTableModel
        }

        suspend fun getAll(context: Context) : List<ActorTableModel>? = withContext(Dispatchers.IO){
            db = initializeDB(context)
            actorTableModelAll = db!!.actorDAO().get()
            return@withContext actorTableModelAll
        }

    }
}