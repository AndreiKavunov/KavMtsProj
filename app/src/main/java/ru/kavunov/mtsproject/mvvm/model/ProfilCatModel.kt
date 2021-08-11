package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.ProfilCategTableModel

class ProfilCatModel {

    companion object {

        var db: AppDatabase? = null

        var profilCatModelTableModel: ProfilCategTableModel? = null
        var profilCatModelAllTableModel: List<ProfilCategTableModel>? = null

        fun initializeDB(context: Context): AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, idPr: Long, idCt: Long, ) = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilCategTableModel(idPr, idCt)
                db!!.profilCategDAO().insert(loginDetails)
            }
        }

        suspend fun getLoginDetails(context: Context, id: Long): ProfilCategTableModel? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilCatModelTableModel = db!!.profilCategDAO().getByName(id)
            return@withContext profilCatModelTableModel
        }

        suspend fun getAll(context: Context): List<ProfilCategTableModel>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilCatModelAllTableModel = db!!.profilCategDAO().get()
            return@withContext profilCatModelAllTableModel
        }

    }
}