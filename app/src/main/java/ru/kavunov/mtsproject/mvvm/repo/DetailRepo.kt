package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.MovListWithAct
import ru.kavunov.mtsproject.bd.MovieTable

class DetailRepo(val movie: MovieTable, val listM: List<MovListWithAct>){
    fun refreshDataDet(onDataReadyCallback1: OnDataReadyCallbackDetail1, onDataReadyCallback2: OnDataReadyCallbackDetail2){

       var listAct: ArrayList<ActorTable> = ArrayList()
        listAct.addAll(listM.getOrNull(0)?.listAct!!)

        val movieDto = MovieDto(title = movie.title, description = movie.description, rateScore = movie.rateScore,
            ageRestriction = movie.ageRestriction.toInt(), imageUrl= movie.imageUrl, actor = listOf(
                Actors(
                    img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
                    name = "Jason Statham"
                ),
                ))

        onDataReadyCallback1.onDataReady1(movieDto)
        onDataReadyCallback2.onDataReady2(listAct)
    }
}
interface OnDataReadyCallbackDetail1 {
    fun onDataReady1(data: MovieDto)
}

interface OnDataReadyCallbackDetail2 {
    fun onDataReady2(data: List<ActorTable>)
}

