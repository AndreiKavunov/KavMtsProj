package ru.kavunov.mtsproject.mvvm

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.*
import ru.kavunov.mtsproject.DTC.MovieDto

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
import java.security.AccessController.getContext

typealias MyViewState = ListFilmFragment.ViewState
typealias MyViewStateUpdate = ListFilmFragment.ViewStateUpdete
class MvvmViewModelMovie(application: Application) : AndroidViewModel(application) {
//class MvvmViewModelMovie: ViewModel() {
    private var job: Job? = null
    lateinit var movieModel: RepoMovie
    val contextM: Context = getApplication()

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    val viewStateUp: LiveData<MyViewStateUpdate> get() = _viewStateUp
    private val _viewStateUp = MutableLiveData<MyViewStateUpdate>()

    val listmovie: LiveData<List<MovieDto>> get() = _listmovie
    var _listmovie = MutableLiveData<List<MovieDto>>()
    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("tagErr","handled $exception")
        _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
        Toast.makeText(contextM, "Не удалось получить данные, повторите попытку.", Toast.LENGTH_SHORT).show()

    }
fun loadMovie(){

    CoroutineScope(Dispatchers.Main).launch() {
      movieModel = RepoMovie(MoviesDataSourceImpl().getMovies())
      movieModel.refreshData( object : OnDataReadyCallback {
        override  fun onDataReady(data: List<List<MovieDto>>) {
            _listmovie.postValue(data[ListFilm.flag])
            changeListF(data[ListFilm.flag])
            _viewState.postValue(MyViewState(isDownloaded = false))
        }
    }
    )

    }
}
    fun updateMovie(){


        job?.cancel()
        job = CoroutineScope(Dispatchers.Main).launch(handler) {

            _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = true))
            val x = (1..3).random()
            Log.d("tagErr",x.toString())
            if (x==1)Integer.parseInt("one")
            if(ListFilm.flag == 0) ListFilm.flag = 1 else ListFilm.flag = 0
            withContext(Dispatchers.IO) { movieModel = RepoMovie(MoviesDataSourceImpl().getMovies())
     }
            movieModel.refreshData( object : OnDataReadyCallback {

                override  fun onDataReady(data: List<List<MovieDto>>) {

                    _listmovie.postValue(data[ListFilm.flag])
                    changeListF(data[ListFilm.flag])

                    _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))

                }
            }
            )

        }
    }

    fun changeListF(movie: List<MovieDto>){
        ListFilm.listMov.clear()
        ListFilm.listMov.addAll(movie)

    }

}