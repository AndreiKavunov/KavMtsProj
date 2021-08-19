package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.recponse.IMG_HEADER
import ru.kavunov.mtsproject.recponse.respModel.AgeRecpModel
import ru.kavunov.mtsproject.recponse.respModel.MovieRecpModel


class MovieRepo(){
    fun refreshData( onDataReadyCallback: OnDataReadyCallback){
        CoroutineScope(Dispatchers.Main).launch() {
            var list: ArrayList<MovieTable>? = ArrayList()
            var listMov: ArrayList<MovieDto>? = ArrayList()
            val listRep = MovieRecpModel.getAll()


            if(listRep != null)for(i in listRep) {
                ListFilm.idFilmAge = i.id.toString()
                var age = AgeRecpModel.getAll()
                list?.add(MovieTable(movId= i.id.toLong(), title= i.title, description= i.overview,
                    rateScore= i.vote_average/2, ageRestriction= age, imageUrl = IMG_HEADER + i.poster_path,
                     backdrop_path = IMG_HEADER + i.poster_path))

                listMov?.add(MovieDto(id=i.id.toString(), title=i.title, backdrop_path= "d", description=i.overview,
                    release_date = i.release_date, rateScore =i.vote_average/2, ageRestriction = age,
                    imageUrl = IMG_HEADER + i.poster_path, actor = listOf(Actors(img = "g",name = "L" )),
                        genre = genreOnId(i.genre_ids[0].toLong())
                    ))

            }

            ListFilm.listMovForDetail.clear()
            if (listMov != null) {
                ListFilm.listMovForDetail.addAll(listMov)
            }

            if (list!=null)onDataReadyCallback.onDataReady(list)
        }}
}
interface OnDataReadyCallback {
    fun onDataReady(data: List<MovieTable>)

}

