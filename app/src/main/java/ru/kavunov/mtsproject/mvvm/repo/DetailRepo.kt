package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.ActorTableModel
import ru.kavunov.mtsproject.bd.MovListWithAct
import ru.kavunov.mtsproject.bd.MovieTableModel

class DetailRepo(val movie: MovieTableModel, val listM: List<MovListWithAct>){
    fun refreshDataDet(onDataReadyCallback1: OnDataReadyCallbackDetail1, onDataReadyCallback2: OnDataReadyCallbackDetail2){

       var listAct: ArrayList<ActorTableModel> = ArrayList()
        listAct.addAll(listM.getOrNull(0)?.listAct!!)

        onDataReadyCallback1.onDataReady1(movie)
        onDataReadyCallback2.onDataReady2(listAct)
    }
}
interface OnDataReadyCallbackDetail1 {
    fun onDataReady1(data: MovieTableModel)
}

interface OnDataReadyCallbackDetail2 {
    fun onDataReady2(data: List<ActorTableModel>)
}

