package ru.kavunov.mtsproject.mvvm

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.MovListWithAct
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.MovieModel
import ru.kavunov.mtsproject.recponse.*




class DetailRepo(position: Long){
    val position = position
    fun refreshDataDet(contetx: Context, onCallbackMovD: OnCallbackMovD,
                       OnCallbacActT: OnCallbacActT){
        CoroutineScope(Dispatchers.Main).launch() {

        val movie: MovieTable? = MovieModel.getMovieID(contetx, position)

        val listM: List<MovListWithAct>? = MovieModel.getActorList(contetx, position)
       var listAct: ArrayList<ActorTable> = ArrayList()
        listM?.getOrNull(0)?.listAct?.let { listAct.addAll(it) }

        val movieDto =
            movie?.title?.let {
                MovieDto(id = movie.movId.toString() ,title = it, description = movie?.description, rateScore = movie.rateScore,
                    ageRestriction = movie?.ageRestriction, imageUrl= movie.imageUrl, releaseDate= movie.releaseDate, genre = movie.genre)
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

suspend fun genreOnId(id: Long): String= withContext(Dispatchers.IO){

    var genre= "empty"
    var listCat = getAllCateg()

        if (listCat != null) {
            for (i in listCat) {
                if (i.id == id.toInt()) genre = i.name
            }
        }
        return@withContext genre
}

suspend fun getAllActors(idF:String) : List<ActorResp>? = withContext(Dispatchers.IO){
    var actors: List<ActorResp>
    try {
        actors = withContext(Dispatchers.IO) {
            App.instance.apiService.getActor(idfilm=idF).cast
        }
    } catch (e: Exception) {
        actors = ArrayList()
    }
    return@withContext actors
}

suspend fun getAllMovie() : List<MovieResponse>? = withContext(Dispatchers.IO){
    var movies: List<MovieResponse>
    try {
        movies = withContext(Dispatchers.IO) {
            App.instance.apiService.getMovie().results
        }
    } catch (e: Exception) {
        movies = ArrayList()
    }
    return@withContext movies
}

suspend fun getAllCateg() : List<CategResp>? = withContext(Dispatchers.IO){
    var categs: List<CategResp>
    try {
        categs = withContext(Dispatchers.IO) {
            App.instance.apiService.getCateg().genres
        }
    } catch (e: Exception) {
        categs = ArrayList()
    }
    return@withContext categs
}

suspend fun getAllAge(language: String, idF: String) : String = withContext(Dispatchers.IO){
    var listAge: List<AgeResp>
    var certification= "No"
    try {
        listAge = withContext(Dispatchers.IO) {
            App.instance.apiService.getAge(idfilm=idF).results
        }
        for (i in listAge){
            if(i.iso31661== language) {
                certification = i.releaseDates[0].certification
            }
        }
    } catch (e: Exception) {
    }
    if(certification.length == 0)certification = "No"

    return@withContext certification
}