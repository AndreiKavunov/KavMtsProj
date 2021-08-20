package ru.kavunov.mtsproject.recponse.respModel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.recponse.ActorResp

import ru.kavunov.mtsproject.recponse.App

class ActorRecpModel {
    companion object {
        suspend fun getAll(idF:String) : List<ActorResp>? = withContext(Dispatchers.IO){
            var actors: List<ActorResp>
            try {
                actors = withContext(Dispatchers.IO) {
                    App.instance.apiService.getActor(idfilm=idF).cast
                }
            } catch (e: Exception) {
                actors = ArrayList()
            }
            return@withContext actors
        }


    }
}