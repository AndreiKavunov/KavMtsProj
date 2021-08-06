package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.DTC.MovieDto

class RepoDetail(val list: List<List<MovieDto>>){
   fun refreshData(onDataReadyCallback: OnDataReadyCallbackDetail){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallbackDetail {
    fun onDataReady(data: List<List<MovieDto>>)

}
