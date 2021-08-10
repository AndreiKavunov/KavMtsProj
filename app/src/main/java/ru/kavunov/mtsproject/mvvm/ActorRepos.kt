package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.ActorModel
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.CategoryModel

class ActorRepos {
    companion object {

        var db: AppDatabase? = null

        var actorModel: ActorModel? = null
        var actorModelAll: List<ActorModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, id: Long, imgAct: String, nameAct: String) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ActorModel(id, imgAct, nameAct)
                db!!.actorDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, id: Long) : ActorModel? {
            db = initializeDB(context)
            actorModel = db!!.actorDAO().getByName(id)
            return actorModel
        }

        fun getAll(context: Context) : List<ActorModel>? {
            db = initializeDB(context)
            actorModelAll = db!!.actorDAO().get()
            return actorModelAll
        }

    }
}