package ru.kavunov.mtsproject.mvvm.model

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.AppDatabase
import ru.kavunov.mtsproject.bd.CategoryTable


class CategModel {

    companion object {

        var db: AppDatabase? = null

        var categTable: CategoryTable? = null
        var categTableAll: List<CategoryTable>? = null

        fun initializeDB(context: Context) : AppDatabase {
            return AppDatabase.getDataseClient(context)
        }

        suspend fun insertData(context: Context, id: Long, category: String) = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            val loginDetails = CategoryTable(id, category)
            db?.categDAO()?.insert(loginDetails)

        }

        suspend fun getLoginDetails(context: Context, id: Long) : CategoryTable? = withContext(Dispatchers.IO) {
            db = initializeDB(context)
            categTable = db?.categDAO()?.getByName(id)
            return@withContext categTable
        }

        suspend fun getAll(context: Context) : List<CategoryTable>? = withContext(Dispatchers.IO){
            db = initializeDB(context)
            categTableAll = db?.categDAO()?.get()
            return@withContext categTableAll
        }

    }
}