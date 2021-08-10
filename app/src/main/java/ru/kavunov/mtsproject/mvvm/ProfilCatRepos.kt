package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.CategoryModel
import ru.kavunov.mtsproject.bd.ProfilCateg

class ProfilCatRepos {

    companion object {

        var db: AppDatabase? = null

        var profilCatModel: ProfilCateg? = null
        var profilCatModelAll: List<ProfilCateg>? = null

        fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, idPr: Long, idCt: Long, ) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilCateg(idPr, idCt)
                db!!.profilCategDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, id: Long): ProfilCateg? {
            db = initializeDB(context)
            profilCatModel = db!!.profilCategDAO().getByName(id)
            return profilCatModel
        }

        fun getAll(context: Context): List<ProfilCateg>? {
            db = initializeDB(context)
            profilCatModelAll = db!!.profilCategDAO().get()
            return profilCatModelAll
        }

    }
}