package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.MovListWithAct
import ru.kavunov.mtsproject.bd.MovieTable

class DetailRepo(val movie: MovieTable, val listM: List<MovListWithAct>){
    fun refreshDataDet(onDataReadyCallback1: OnDataReadyCallbackDetail1, onDataReadyCallback2: OnDataReadyCallbackDetail2){

       var listAct: ArrayList<ActorTable> = ArrayList()
        listAct.addAll(listM.getOrNull(0)?.listAct!!)

        onDataReadyCallback1.onDataReady1(movie)
        onDataReadyCallback2.onDataReady2(listAct)
    }
}
interface OnDataReadyCallbackDetail1 {
    fun onDataReady1(data: MovieTable)
}

interface OnDataReadyCallbackDetail2 {
    fun onDataReady2(data: List<ActorTable>)
}

