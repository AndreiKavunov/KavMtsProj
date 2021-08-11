package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.ProfListWithCateg
import ru.kavunov.mtsproject.bd.ProfilTableModel


class ProfilModel {

    companion object {

        var db: AppDatabase? = null

        var profilTableModel: ProfilTableModel? = null
        var profilTableModel1: List<ProfilTableModel>? = null
        var profilTEST: List<ProfListWithCateg>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long,name: String, email: String, phone: String, foto: String,)= withContext(Dispatchers.IO) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilTableModel(id, name, email, phone, foto)
                db!!.profilDAO().insert(loginDetails)
            }
        }

        suspend fun getProfilId(context: Context, id: Long) : ProfilTableModel? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilTableModel = db!!.profilDAO().getByName(id)
            return@withContext profilTableModel
        }

        suspend fun getAll(context: Context) : List<ProfilTableModel>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilTableModel1 = db!!.profilDAO().get()
            return@withContext profilTableModel1
        }

        suspend fun getCategList(context: Context, id: Long) : List<ProfListWithCateg>? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            profilTEST = db!!.profilDAO().getProflistsWithCat(id)
            return@withContext profilTEST
        }
    }
}