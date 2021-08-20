package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.recponse.ActorResp

import ru.kavunov.mtsproject.recponse.IMG_HEADER
import ru.kavunov.mtsproject.recponse.respModel.ActorRecpModel
import ru.kavunov.mtsproject.recponse.respModel.CategRecpModel
import ru.kavunov.mtsproject.recponse.respModel.MovieRecpModel

class DetailRepo(position: Long){
     val position = position
    fun refreshDataDet(onCallbackMovD: OnCallbackMovD,
                       OnCallbacActT: OnCallbacActT){
        CoroutineScope(Dispatchers.Main).launch() {
//            lateinit var movie: MovieDto
            lateinit var movie: MovieDto

           val listmodel = MovieRecpModel.getAll()
            if (listmodel != null) {
                for(i in listmodel) {
                    if( i.id == position.toInt()) {
                        movie = i
                    }
                }
            }
       val listM: List<ActorResp>? = ActorRecpModel.getAll(position.toString())
       var listAct: ArrayList<ActorTable> = ArrayList()
            if (listM != null) {
                for(i in listM){
                    if(i.profile_path!=null) {
                        listAct.add(
                            ActorTable(
                                actId = i.id.toLong(),
                                imgAct = IMG_HEADER + i.profile_path,
                                nameAct = i.name
                            )
                        )
                    }
                }
            }



            if (movie != null) {
                onCallbackMovD.onDataMovD(movie)
            }
        OnCallbacActT.onDataActT(listAct)
    }}
}
interface OnCallbackMovD {
    fun onDataMovD(data: MovieDto)
}

interface OnCallbacActT {
    fun onDataActT(data: List<ActorTable>)
}

suspend fun genreOnId(id: Long): String= withContext(Dispatchers.IO){

    var genre= "empty"
    var listCat = CategRecpModel.getAll()

        if (listCat != null) {
            for (i in listCat) {
                if (i.id == id.toInt()) genre = i.name
            }
        }
        return@withContext genre
}

