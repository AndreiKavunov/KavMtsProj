package ru.kavunov.mtsproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto

import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MvvmViewModelCateg: ViewModel() {
    val categList: LiveData<List<Categorie>> get() = _categList
    private val _categList = MutableLiveData<List<Categorie>>()

    fun loadCateg() {
        _categList.postValue(CategoryDataSourceImpl().getMovies())
    }

}