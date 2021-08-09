package ru.kavunov.mtsproject.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.ProfilModel


class ProfilRepos {

    companion object {

        var db: AppDatabase? = null

        var profilModel: ProfilModel? = null
        var profilModel1: List<ProfilModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, name: String, email: String, phone: String, foto: String,
                       interests1: String, interests2: String, interests3: String) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = ProfilModel(name, email, phone, foto, interests1, interests2, interests3)
                db!!.profilDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, username: String) : ProfilModel? {
            db = initializeDB(context)
            profilModel = db!!.profilDAO().getByName(username)
            return profilModel
        }

        fun getAll(context: Context) : List<ProfilModel>? {
            db = initializeDB(context)
            profilModel1 = db!!.profilDAO().get()
            return profilModel1
        }

    }
}