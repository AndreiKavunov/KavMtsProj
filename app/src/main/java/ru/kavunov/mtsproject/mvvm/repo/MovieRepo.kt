package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.MovieTableModel

class MovieRepo(val list: List<MovieTableModel>){
    fun refreshData(onDataReadyCallback: OnDataReadyCallback){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<MovieTableModel>)

}

