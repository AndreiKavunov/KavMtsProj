package ru.kavunov.mtsproject.recponse.respModel

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.recponse.AgeResp
import ru.kavunov.mtsproject.recponse.AgeRespList
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.Release_dates

class AgeRecpModel {
    companion object {
        suspend fun getAll(idF:String) : String = withContext(Dispatchers.IO){
            var listAge: List<AgeResp>
            var certification= "No"
            try {
                listAge = withContext(Dispatchers.IO) {
                        App.instance.apiService.getAge(idfilm=idF).results
                }
                for (i in listAge){
                    if(i.iso_3166_1=="RU") {
                        certification = i.release_dates[0].certification
                    }
                }
            } catch (e: Exception) {
                certification = "E"
            }
            if(certification.length == 0)certification = "No"

            return@withContext certification
        }

    }
}