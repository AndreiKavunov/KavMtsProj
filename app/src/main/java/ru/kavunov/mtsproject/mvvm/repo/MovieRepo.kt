package ru.kavunov.mtsproject.mvvm

import android.content.Context
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.model.*
import ru.kavunov.mtsproject.recponse.AgeResp
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.CategResp
import ru.kavunov.mtsproject.recponse.IMG_HEADER


class MovieRepo(){
    fun refreshData(contetx: Context, callbackMovie: CallbackMovie, callbackCateg: CallbackCateg){
    CoroutineScope(Dispatchers.Main).launch() {
        startBd(contetx)
        val contextA = contetx
        val list: List<MovieTable>? = MovieModel.getAll(contextA)

        if (list!=null)callbackMovie.onDataReadyM(list)

        val listC: List<CategoryTable>? = CategModel.getAll(contetx)
        if (listC!=null)callbackCateg.onDataReadyC(listC)
    }}
}


interface CallbackCateg {
    fun onDataReadyC(data: List<CategoryTable>)

}
interface CallbackMovie {
    fun onDataReadyM(data: List<MovieTable>)

}

suspend fun getAllMov() : List<MovieResponse>? = withContext(Dispatchers.IO){
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

suspend fun getAllA(idF:String) : String = withContext(Dispatchers.IO){
    var listAge: List<AgeResp>
    var certification= "No"
    try {
        listAge = withContext(Dispatchers.IO) {
            App.instance.apiService.getAge(idfilm=idF).results
        }
        for (i in listAge){
            if(i.iso31661=="RU") {
                certification = i.releaseDates[0].certification
            }
        }
    } catch (e: Exception) {
        certification = "E"
    }
    if(certification.length == 0)certification = "No"

    return@withContext certification
}

suspend fun startBd(context: Context)= withContext(Dispatchers.IO){
        if(MovieModel.getAll(context)?.size == 0){
            ProfilCatModel.insertData(context, 1, 28)
            ProfilCatModel.insertData(context, 1, 12)
            ProfilCatModel.insertData(context, 1, 16)
            ProfilModel.insertData(
                context,
                id = 1,
                name = "Иван",
                email = "Ivan@mail.ru",
                phone = "8-909-000-9999",
                foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
            )

            val listCateg = getAllCat()
            Log.d("tag11", "555" +listCateg.toString())
            if (listCateg != null) {
                for(i in listCateg) {
                    CategModel.insertData(context, i.id.toLong(), i.name)
                }

            }
            val listMov = getAllMov()
            if (listMov != null) {
                var idF = 0L
                var idA = 0L
                for(i in listMov){
                    idF = i.id.toLong()
                    var age = getAllA(i.id.toString())
                    var genre = CategModel.getIdGence(context, i.genreIds[0].toLong())!!.category
                    val listActor = getAllActors(i.id.toString())
                    MovieModel.insertData(context= context, id= i.id.toLong(), title = i.title, description= i.overview,
                        imageUrl = IMG_HEADER + i.posterPath, ageRestriction = age, rateScore = i.voteAverage/2,
                        releaseDate= i.releaseDate, genre = genre)

                    if (listActor != null) {
                        for (actor in listActor.take(5)){
                            idA = actor.id.toLong()
                            ActorModel.insertData(context = context, id = actor.id.toLong(),
                                imgAct = IMG_HEADER + actor.profilePath, nameAct = actor.name)
                            MovieActModel.insertData(context, idF, idA)

                        }


                    }
                }}

        }

}

suspend fun changeBd(context: Context)= withContext(Dispatchers.IO){
        MovieModel.deleteAll(context)
        ActorModel.deleteAll(context)
        MovieActModel.deleteAll(context)
        val listMov = getAllMov()
        if (listMov != null) {
            var idF = 0L
            var idA = 0L
            for(i in listMov){
                idF = i.id.toLong()
                var age = getAllA(i.id.toString())
                var genre = CategModel.getIdGence(context, i.genreIds[0].toLong())!!.category
                val listActor = getAllActors(i.id.toString())
                MovieModel.insertData(context= context, id= i.id.toLong(), title = i.title, description= i.overview,
                    imageUrl = IMG_HEADER + i.posterPath, ageRestriction = age, rateScore = i.voteAverage/2,
                    releaseDate= i.releaseDate, genre = genre)

                if (listActor != null) {
                    for (actor in listActor.take(5)){
                        idA = actor.id.toLong()
                        ActorModel.insertData(context = context, id = actor.id.toLong(),
                            imgAct = IMG_HEADER + actor.profilePath, nameAct = actor.name)
                        MovieActModel.insertData(context, idF, idA)

                    }


                }
            }}



}
suspend fun getAllCat() : List<CategResp>? = withContext(Dispatchers.IO){
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
