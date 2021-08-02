package ru.kavunov.mtsproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto

import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MvvmViewModelActor: ViewModel() {
    val actorList: LiveData<List<Actors>> get() = _actorList
    private val _actorList = MutableLiveData<List<Actors>>()

//    fun loadCateg() {
//        _actorList.postValue(CategoryDataSourceImpl().getMovies())
//    }

}