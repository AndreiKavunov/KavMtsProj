package ru.kavunov.mtsproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.MainActivity

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
typealias MyViewState = MainActivity.ViewState

class MvvmViewModelMovie: ViewModel() {
    val l = MoviesDataSourceImpl()
    var movieModel: RepoMovie = RepoMovie(l.getMovies())
    val text: LiveData<List<MovieDto>> get() = _text
    var _text = MutableLiveData<List<MovieDto>>()
    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()


    val dataList: LiveData<List<MovieDto>> get() = _dataList
    private val _dataList = MutableLiveData<List<MovieDto>>()

//    val onDataReadyCallback = object : OnDataReadyCallback {
//        override fun onDataReady(data: String) {
//            text.set(data)
//        }
//    }
//
//    fun refresh(){
//
//        repoModel.refreshData(onDataReadyCallback)
//    }

    suspend fun loadMovie()  = withContext(Dispatchers.IO){
        movieModel.refreshData( object : OnDataReadyCallback {
            override fun onDataReady(data: List<List<MovieDto>>) {
                _text.postValue(data[ListFilm.flag])
                _viewState.postValue(MyViewState(isDownloaded = false))
            }
        }
        )}

//    suspend fun loadMovie() = withContext(Dispatchers.IO) {
//        _dataList.postValue(MoviesDataSourceImpl().getMovies()[ListFilm.flag])
//    }
}