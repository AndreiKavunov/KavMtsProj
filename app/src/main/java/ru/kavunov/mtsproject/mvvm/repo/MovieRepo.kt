package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.MovieModel
import ru.kavunov.mtsproject.recponse.respModel.CategRecpModel
import ru.kavunov.mtsproject.recponse.respModel.MovieRecpModel

//class MovieRepo(val list: List<MovieTable>){
//class MovieRepo(){
//    fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallback){
//    CoroutineScope(Dispatchers.Main).launch() {
//        val contextA = contetx
//        val list: List<MovieTable>? = MovieModel.getAll(contextA)
//
//        if (list!=null)onDataReadyCallback.onDataReady(list)
//    }}
//}

class MovieRepo(){
    fun refreshData( onDataReadyCallback: OnDataReadyCallback){
        CoroutineScope(Dispatchers.Main).launch() {
            var list: ArrayList<MovieTable>? = ArrayList()
            val listRep = MovieRecpModel.getAll()
            ListFilm.listMovRecp.addAll(listRep)
            if(listRep != null)for(i in listRep) {
                list?.add(MovieTable(movId= i.id.toLong(), title= i.title, description= i.overview,
                    rateScore= (i.vote_average/2).toInt(), ageRestriction="18", imageUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg"))
            }

            if (list!=null)onDataReadyCallback.onDataReady(list)
        }}
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<MovieTable>)

}

