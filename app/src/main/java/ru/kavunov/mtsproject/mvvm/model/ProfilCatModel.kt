package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.ProfilCategTable

class ProfilCatModel {

    companion object {

        var db: AppDatabase? = null

        var profilCatModelTable: ProfilCategTable? = null
        var profilCatModelAllTable: List<ProfilCategTable>? = null

        fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, idPr: Long, idCt: Long, ) = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilCategTable(idPr, idCt)
                db?.profilCategDAO()?.insert(loginDetails)
            }
        }

        suspend fun getLoginDetails(context: Context, id: Long): ProfilCategTable? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilCatModelTable = db?.profilCategDAO()?.getByName(id)
            return@withContext profilCatModelTable
        }

        suspend fun getAll(context: Context): List<ProfilCategTable>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilCatModelAllTable = db?.profilCategDAO()?.get()
            return@withContext profilCatModelAllTable
        }

    }
}