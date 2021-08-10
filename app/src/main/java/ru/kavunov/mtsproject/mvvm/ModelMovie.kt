package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto

class RepoMovie(val list: List<List<MovieDto>>){
   suspend fun refreshData(onDataReadyCallback: OnDataReadyCallback)= withContext(Dispatchers.IO){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<List<MovieDto>>)

}
