package ru.kavunov.mtsproject.mvvm.viewModel


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.*
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.MovieRepo
import ru.kavunov.mtsproject.mvvm.OnDataReadyCallback
import ru.kavunov.mtsproject.mvvm.model.*
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl

import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

typealias MyViewState = ListFilmFragment.ViewState
typealias MyViewStateUpdate = ListFilmFragment.ViewStateUpdete
class MovieViewModel(application: Application) : AndroidViewModel(application) {
    //class MvvmViewModelMovie: ViewModel() {
    private var job: Job? = null
    lateinit var movieRepo: MovieRepo
    val contextM: Context = getApplication()

    val viewState: LiveData<MyViewState> get() = _viewState
    private val _viewState = MutableLiveData<MyViewState>()

    val viewStateUp: LiveData<MyViewStateUpdate> get() = _viewStateUp
    private val _viewStateUp = MutableLiveData<MyViewStateUpdate>()

    val listmovie: LiveData<List<MovieTable>> get() = _listmovie
    var _listmovie = MutableLiveData<List<MovieTable>>()

    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("tagErr","handled $exception")
        _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
        Toast.makeText(contextM, "Не удалось получить данные, повторите попытку.", Toast.LENGTH_SHORT).show()

    }
    fun loadMovie(){

        CoroutineScope(Dispatchers.Main).launch() {
            startBd(contextM)
            movieRepo = MovieRepo()

            movieRepo.refreshData(object : OnDataReadyCallback {

                override  fun onDataReady(data: List<MovieTable>) {
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
            if (x==4)Integer.parseInt("one")
            movieRepo = MovieRepo()

            movieRepo.refreshData(object : OnDataReadyCallback {

                override  fun onDataReady(data: List<MovieTable>) {
                    var list : List<MovieTable> = data.shuffled()
                    _listmovie.postValue(list)
                    changeListF(list)
                    _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
                }
            }
            )
        }
    }

    fun changeListF(movie: List<MovieTable>){
        ListFilm.listMov.clear()
        ListFilm.listMov.addAll(movie)
    }

    fun startBd(context: Context){
        CoroutineScope(Dispatchers.IO).launch() {
            if(ActorModel.getAll(context)?.size == 0){
                ProfilCatModel.insertData(context, 1, 2)
                ProfilCatModel.insertData(context, 1, 4)
                ProfilCatModel.insertData(context, 1, 5)
                ProfilModel.insertData(
                    context,
                    id = 1,
                    name = "Иван",
                    email = "Ivan@mail.ru",
                    phone = "8-909-000-9999",
                    foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
                )
                for (x in CategoryDataSourceImpl().getMovies()) CategModel.insertData(
                    context,
                    0,
                    x.category
                )
                var idF = 0L
                var idA = 0L
                for (x in MoviesDataSourceImpl().getMovies()[0]) {
                    idF ++
                    MovieModel.insertData(
                        context, id = idF, title = x.title, description = x.description,

                        rateScore = x.rateScore, ageRestriction = x.ageRestriction.toString(),
                        imageUrl = x.imageUrl, backdrop_path= x.backdrop_path

                    )

                    for(i in x.actor){
                        idA++
                        ActorModel.insertData(context, id = idA, imgAct = i.img, nameAct= i.name)
                        MovieActModel.insertData(context, idF, idA)}
                }
            }}}
}
