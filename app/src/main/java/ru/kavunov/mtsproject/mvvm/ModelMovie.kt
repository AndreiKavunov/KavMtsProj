package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.DTC.MovieDto

class RepoMovie(val list: List<List<MovieDto>>){
   fun refreshData(onDataReadyCallback: OnDataReadyCallback){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<List<MovieDto>>)

}
