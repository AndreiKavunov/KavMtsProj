package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.recponse.*





class DetailRepo(position: Long){
     val position = position
    fun refreshDataDet(onCallbackMovD: OnCallbackMovD,
                       OnCallbacActT: OnCallbacActT){
        CoroutineScope(Dispatchers.Main).launch() {
            lateinit var movie: MovieDto

           val listmodel = getAllMovie()
            var listMov: ArrayList<MovieDto>? = ArrayList()
            if(listmodel != null)for(i in listmodel){
                var age = getAllAge(LANGUAGE, i.id.toString())
                listMov?.add(MovieDto(id=i.id.toString(), title=i.title,  description=i.overview,
                    releaseDate = i.releaseDate, rateScore =i.voteAverage/2, ageRestriction = age,
                    imageUrl = IMG_HEADER + i.posterPath,
                    genre = genreOnId(i.genreIds[0].toLong())
                    ))
            }
            if (listMov != null) {
                for(i in listMov) {
                    if( i.id == position.toString()) {
                        movie = i
                    }
                }
            }
       val listM: List<ActorResp>? = getAllActors(position.toString())
       var listAct: ArrayList<ActorTable> = ArrayList()
            if (listM != null) {
                for(i in listM){
                    if(i.profilePath!=null) {
                        listAct.add(
                            ActorTable(
                                actId = i.id.toLong(),
                                imgAct = IMG_HEADER + i.profilePath,
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

suspend fun genreOnId(id: Long): String{

    var genre= "empty"

    var listCat = getAllCateg()
    withContext(Dispatchers.IO){
        if (listCat != null) {
            for (i in listCat) {
                if (i.id == id.toInt()) genre = i.name
            }
        }}
        return genre
}

suspend fun getAllActors(idF:String) : List<ActorResp>? {
    var actors: List<ActorResp>
    withContext(Dispatchers.IO){
    try {
        actors = withContext(Dispatchers.IO) {
            App.instance.apiService.getActor(idfilm=idF).cast
        }
    } catch (e: Exception) {
        actors = ArrayList()
    }}
    return actors
}

suspend fun getAllMovie() : List<MovieResponse>?{
    var movies: List<MovieResponse>
    withContext(Dispatchers.IO){
    try {
        movies = withContext(Dispatchers.IO) {
            App.instance.apiService.getMovie().results
        }
    } catch (e: Exception) {
        movies = ArrayList()
    }}
        return movies
}

suspend fun getAllCateg() : List<CategResp>?{
    var categs: List<CategResp>
    withContext(Dispatchers.IO){
    try {
        categs = withContext(Dispatchers.IO) {
            App.instance.apiService.getCateg().genres
        }
    } catch (e: Exception) {
        categs = ArrayList()
    }}
    return categs
}

suspend fun getAllAge(language: String, idF: String) : String{

    var listAge: List<AgeResp>
    var certification= "No"
    withContext(Dispatchers.IO) {
        try {
            listAge = withContext(Dispatchers.IO) {
                App.instance.apiService.getAge(idfilm = idF).results
            }
            for (i in listAge) {
                if (i.iso31661 == language) {
                    certification = i.releaseDates[0].certification
                }
            }
        } catch (e: Exception) {
        }
        if (certification.length == 0) certification = "No"
    }
    return certification
}
