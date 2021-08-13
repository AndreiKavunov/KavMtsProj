package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.MovListWithAct
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.MovieModel

//class DetailRepo(val movie: MovieTable, val listM: List<MovListWithAct>){
class DetailRepo(position: Long){
    val position = position
    fun refreshDataDet(contetx: Context, onCallbackMovD: OnCallbackMovD,
                       OnCallbacActT: OnCallbacActT){
        CoroutineScope(Dispatchers.Main).launch() {
        val movie: MovieTable? = MovieModel.getMovieID(contetx, position)
        val listM: List<MovListWithAct>? = MovieModel.getActorList(contetx, position)
       var listAct: ArrayList<ActorTable> = ArrayList()
//        listAct.addAll(listM.getOrNull(0)?.listAct)
        listM?.getOrNull(0)?.listAct?.let { listAct.addAll(it) }

        val movieDto =
            movie?.title?.let {
                MovieDto(title = it, description = movie?.description, rateScore = movie.rateScore,
                    ageRestriction = movie?.ageRestriction.toInt(), imageUrl= movie.imageUrl, actor = listOf(
                        Actors(
                            img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
                            name = "Jason Statham"
                        ),
                    ))
            }

            if (movieDto != null) {
                onCallbackMovD.onDataMovD(movieDto)
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

