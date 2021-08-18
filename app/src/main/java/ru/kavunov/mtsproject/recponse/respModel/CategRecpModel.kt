package ru.kavunov.mtsproject.recponse.respModel

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.mvvm.model.CategModel
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.CategResp

class CategRecpModel {
    companion object {
        suspend fun getAll() : List<CategResp>? = withContext(Dispatchers.IO){
            var categs: List<CategResp>
            try {
                categs = withContext(Dispatchers.IO) {
                    App.instance.apiService.getCateg().genres
                }
            } catch (e: Exception) {
                categs = ArrayList()
            }
            return@withContext categs
        }


    }
}