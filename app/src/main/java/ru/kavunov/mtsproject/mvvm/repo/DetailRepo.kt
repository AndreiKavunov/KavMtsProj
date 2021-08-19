package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.recponse.ActorResp

import ru.kavunov.mtsproject.recponse.IMG_HEADER
import ru.kavunov.mtsproject.recponse.respModel.ActorRecpModel

//class DetailRepo(val movie: MovieTable, val listM: List<MovListWithAct>){
//class DetailRepo(position: Long){
//    val position = position
//    fun refreshDataDet(contetx: Context, onCallbackMovD: OnCallbackMovD,
//                       OnCallbacActT: OnCallbacActT){
//        CoroutineScope(Dispatchers.Main).launch() {
//
//        val movie: MovieTable? = MovieModel.getMovieID(contetx, position)
//
//        val listM: List<MovListWithAct>? = MovieModel.getActorList(contetx, position)
//       var listAct: ArrayList<ActorTable> = ArrayList()
//        listM?.getOrNull(0)?.listAct?.let { listAct.addAll(it) }
//
//        val movieDto =
//            movie?.title?.let {
//                MovieDto(title = it, description = movie?.description, rateScore = movie.rateScore,
//                    ageRestriction = movie?.ageRestriction.toInt(), imageUrl= movie.imageUrl, actor = listOf(
//                        Actors(
//                            img = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
//                            name = "Jason Statham"
//                        ),
//                    ))
//            }
//
//            if (movieDto != null) {
//                onCallbackMovD.onDataMovD(movieDto)
//            }
//        OnCallbacActT.onDataActT(listAct)
//    }}
//}



class DetailRepo(position: Long){
     val position = position
    fun refreshDataDet(onCallbackMovD: OnCallbackMovD,
                       OnCallbacActT: OnCallbacActT){
        CoroutineScope(Dispatchers.Main).launch() {
            lateinit var movie: MovieDto
        for(i in ListFilm.listMovForDetail) {
            if( i.id == position.toString()) {
               movie = i
            }
        }
       val listM: List<ActorResp>? = ActorRecpModel.getAll()
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

fun genreOnId(id: Long): String{
    var genre= "empty"
    if(ListFilm.listCat.size > 0){
        for(i in ListFilm.listCat){
            if(i.categId == id)genre = i.category

        }

    }
    return genre
}
