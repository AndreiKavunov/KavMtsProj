package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.MovieModel

//class MovieRepo(val list: List<MovieTable>){
class MovieRepo(){
    fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallback){
    CoroutineScope(Dispatchers.Main).launch() {
        val contextA = contetx
        val list: List<MovieTable>? = MovieModel.getAll(contextA)

        if (list!=null)onDataReadyCallback.onDataReady(list)
    }}
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<MovieTable>)

}

