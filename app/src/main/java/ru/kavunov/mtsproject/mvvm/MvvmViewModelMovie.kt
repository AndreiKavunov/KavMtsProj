package ru.kavunov.mtsproject.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MvvmViewModelMovie: ViewModel() {
    val dataList: LiveData<List<MovieDto>> get() = _dataList
    private val _dataList = MutableLiveData<List<MovieDto>>()
    var listMov = ArrayList<MovieDto>()


    fun loadMovie() {
        listMov.addAll(MoviesDataSourceImpl().getMovies())
        _dataList.postValue(listMov)
    }
    fun loadMovie1() {
        listMov.addAll(MoviesDataSourceImpl().getMovies())
        _dataList.postValue(listMov)
    }
    suspend fun updateList1() = withContext(Dispatchers.IO){

        listMov.clear()
        while (listMov.size < 6) {
            var film = MoviesDataSourceImpl().getMovies().random()
            if (film !in listMov)
                listMov.add(film)        }
        Thread.sleep(2000)
        _dataList.postValue(listMov)

    }
}