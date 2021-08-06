package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.DTC.MovieDto

class RepoDetail(val list: ArrayList<MovieDto>){
   fun refreshDataDet(onDataReadyCallback: OnDataReadyCallbackDetail){

        onDataReadyCallback.onDataReady2(list)
    }
}
interface OnDataReadyCallbackDetail {
    fun onDataReady2(data: ArrayList<MovieDto>)

}
