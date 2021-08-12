package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.CategoryTableModel


class CategModel {

    companion object {

        var db: AppDatabase? = null

        var categTableModel: CategoryTableModel? = null
        var categTableModelAll: List<CategoryTableModel>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long, category: String) = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            val loginDetails = CategoryTableModel(id, category)
            db!!.categDAO().insert(loginDetails)

        }

        suspend fun getLoginDetails(context: Context, id: Long) : CategoryTableModel? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            categTableModel = db!!.categDAO().getByName(id)
            return@withContext categTableModel
        }

        suspend fun getAll(context: Context) : List<CategoryTableModel>? = withContext(Dispatchers.IO){
            Thread.sleep(2000)
            db = initializeDB(context)
            categTableModelAll = db!!.categDAO().get()
            return@withContext categTableModelAll
        }

    }
}