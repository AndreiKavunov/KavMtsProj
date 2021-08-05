package ru.kavunov.mtsproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.ListFilmFragment
import ru.kavunov.mtsproject.MainActivity

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
typealias MyViewState = ListFilmFragment.ViewState

class MvvmViewModelMovie: ViewModel() {
    lateinit var movieModel: RepoMovie

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    val listmovie: LiveData<List<MovieDto>> get() = _listmovie
    var _listmovie = MutableLiveData<List<MovieDto>>()

suspend fun loadMovie() = withContext(Dispatchers.IO){
    movieModel= RepoMovie(MoviesDataSourceImpl().getMovies())
    movieModel.refreshData( object : OnDataReadyCallback {
        override  fun onDataReady(data: List<List<MovieDto>>) {
            _listmovie.postValue(data[ListFilm.flag])
            _viewState.postValue(MyViewState(isDownloaded = false))

        }
    }
    )}

}