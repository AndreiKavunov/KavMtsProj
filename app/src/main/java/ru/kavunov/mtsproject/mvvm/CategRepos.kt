package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.CategoryModel
import ru.kavunov.mtsproject.bd.ProfilModel




class CategRepos {

    companion object {

        var db: AppDatabase? = null

        var categModel: CategoryModel? = null
        var categModelAll: List<CategoryModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, id: Long, category: String) {
            db = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = CategoryModel(id, category)
                db!!.categDAO().insert(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, id: Long) : CategoryModel? {
            db = initializeDB(context)
            categModel = db!!.categDAO().getByName(id)
            return categModel
        }

        fun getAll(context: Context) : List<CategoryModel>? {
            db = initializeDB(context)
            categModelAll = db!!.categDAO().get()
            return categModelAll
        }

    }
}