package ru.kavunov.mtsproject.mvvm.viewModel


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.*
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.MovieTableModel
import ru.kavunov.mtsproject.mvvm.MovieRepo
import ru.kavunov.mtsproject.mvvm.OnDataReadyCallback
import ru.kavunov.mtsproject.mvvm.model.CategModel
import ru.kavunov.mtsproject.mvvm.model.MovieModel

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

typealias MyViewState = ListFilmFragment.ViewState
typealias MyViewStateUpdate = ListFilmFragment.ViewStateUpdete
class MovieViewModel(application: Application) : AndroidViewModel(application) {
    //class MvvmViewModelMovie: ViewModel() {
    private var job: Job? = null
    lateinit var movieRepoModel: MovieRepo
    val contextM: Context = getApplication()

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    val viewStateUp: LiveData<MyViewStateUpdate> get() = _viewStateUp
    private val _viewStateUp = MutableLiveData<MyViewStateUpdate>()

    val listmovie: LiveData<List<MovieTableModel>> get() = _listmovie
    var _listmovie = MutableLiveData<List<MovieTableModel>>()

    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("tagErr","handled $exception")
        _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
        Toast.makeText(contextM, "Не удалось получить данные, повторите попытку.", Toast.LENGTH_SHORT).show()

    }
    fun loadMovie(){

        CoroutineScope(Dispatchers.Main).launch() {
            movieRepoModel = MovieRepo(MovieModel.getAll(getApplication())!!)
            movieRepoModel.refreshData( object : OnDataReadyCallback {
                override  fun onDataReady(data: List<MovieTableModel>) {
                    _listmovie.postValue(data)
                    changeListF(data)
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
            if (x==4)Integer.parseInt("one")
            movieRepoModel = MovieRepo(MovieModel.getAll(getApplication())!!)
            movieRepoModel.refreshData( object : OnDataReadyCallback {

                override  fun onDataReady(data: List<MovieTableModel>) {
                    var list : List<MovieTableModel> = data.shuffled()
                    _listmovie.postValue(list)
                    changeListF(list)
                    _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
                }
            }
            )
        }
    }

    fun changeListF(movie: List<MovieTableModel>){
        ListFilm.listMov.clear()
        ListFilm.listMov.addAll(movie)
    }
}
