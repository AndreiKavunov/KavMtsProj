package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.ProfListWithCateg
import ru.kavunov.mtsproject.bd.ProfilTable


class ProfilModel {

    companion object {

        var db: AppDatabase? = null

        var profilTable: ProfilTable? = null
        var profilTable1: List<ProfilTable>? = null
        var profilActs: List<ProfListWithCateg>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long,name: String, email: String, phone: String, foto: String,)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilTable(id, name, email, phone, foto)
                db?.profilDAO()?.insert(loginDetails)
            }
        }

        suspend fun getProfilId(context: Context, id: Long) : ProfilTable? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilTable = db?.profilDAO()?.getByName(id)
            return@withContext profilTable
        }

        suspend fun getAll(context: Context) : List<ProfilTable>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilTable1 = db?.profilDAO()?.get()
            return@withContext profilTable1
        }

        suspend fun getCategList(context: Context, id: Long) : List<ProfListWithCateg>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilActs = db?.profilDAO()?.getProflistsWithCat(id)
            return@withContext profilActs
        }
    }
}