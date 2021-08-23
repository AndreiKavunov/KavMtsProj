package ru.kavunov.mtsproject.mvvm

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.MovieModel
import ru.kavunov.mtsproject.recponse.AgeResp
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.IMG_HEADER


class MovieRepo(){
    fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallback){
    CoroutineScope(Dispatchers.Main).launch() {
        val contextA = contetx
        val list: List<MovieTable>? = MovieModel.getAll(contextA)

        if (list!=null)onDataReadyCallback.onDataReady(list)
    }}
}


//class MovieRepo(){
//    fun refreshData( onDataReadyCallback: OnDataReadyCallback){
//        CoroutineScope(Dispatchers.Main).launch() {
//            var list: ArrayList<MovieTable>? = ArrayList()
//            val listRep = getAllMov()
//
//
//            if(listRep != null)for(i in listRep) {
//
//                var age = getAllA(i.id.toString())
//                list?.add(MovieTable(movId= i.id.toLong(), title= i.title, description= i.overview,
//                    rateScore= i.voteAverage/2, ageRestriction= age, imageUrl = IMG_HEADER + i.posterPath,
//                     ))
//
//
//            }
//
//
//            if (list!=null)onDataReadyCallback.onDataReady(list)
//        }}
//}
interface OnDataReadyCallback {
    fun onDataReady(data: List<MovieTable>)

}

suspend fun getAllMov() : List<MovieResponse>? = withContext(Dispatchers.IO){
    var movies: List<MovieResponse>
    try {
        movies = withContext(Dispatchers.IO) {
            App.instance.apiService.getMovie().results
        }
    } catch (e: Exception) {
        movies = ArrayList()
    }

    return@withContext movies
}

suspend fun getAllA(idF:String) : String = withContext(Dispatchers.IO){
    var listAge: List<AgeResp>
    var certification= "No"
    try {
        listAge = withContext(Dispatchers.IO) {
            App.instance.apiService.getAge(idfilm=idF).results
        }
        for (i in listAge){
            if(i.iso31661=="RU") {
                certification = i.releaseDates[0].certification
            }
        }
    } catch (e: Exception) {
        certification = "E"
    }
    if(certification.length == 0)certification = "No"

    return@withContext certification
}

