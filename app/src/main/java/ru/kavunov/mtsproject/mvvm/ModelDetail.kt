package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto

class RepoDetail(val list: ArrayList<MovieDto>){
    suspend fun refreshDataDet(onDataReadyCallback: OnDataReadyCallbackDetail)= withContext(
        Dispatchers.IO){

        onDataReadyCallback.onDataReady2(list)
    }
}
interface OnDataReadyCallbackDetail {
    fun onDataReady2(data: ArrayList<MovieDto>)

}
