package ru.kavunov.mtsproject.mvvm.viewModel


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.*
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.*


typealias MyViewState = ListFilmFragment.ViewState
typealias MyViewStateUpdate = ListFilmFragment.ViewStateUpdete
class MovieViewModel(application: Application) : AndroidViewModel(application) {
//    class MovieViewModel: ViewModel() {

    private var job: Job? = null
    lateinit var movieRepo: MovieRepo

    val listcateg: LiveData<List<CategoryTable>> get() = _listcateg
    var _listcateg = MutableLiveData<List<CategoryTable>>()

    val contextM: Context = getApplication()
    var listMov: MutableLiveData<List<MovieTable>> = MutableLiveData()
    val viewState: LiveData<MyViewState> get() = _viewState

    private val _viewState = MutableLiveData<MyViewState>()
    val viewStateUp: LiveData<MyViewStateUpdate> get() = _viewStateUp
    private val _viewStateUp = MutableLiveData<MyViewStateUpdate>()
    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("tagErr", "handled $exception")
        _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
        Toast.makeText(
            contextM,
            "Не удалось получить данные, повторите попытку.",
            Toast.LENGTH_SHORT
        ).show()

    }

    fun loadMovie(): MutableLiveData<List<MovieTable>> {
        CoroutineScope(Dispatchers.Main).launch() {
            if (listMov.value == null) {
                Log.d("tag11", listMov.value.toString())
                movieRepo = MovieRepo()
                movieRepo.refreshData(getApplication(), object : CallbackMovie {
                    override fun onDataReadyM(data: List<MovieTable>) {
                        listMov.value = data
                        _viewState.postValue(MyViewState(isDownloaded = false))
                    }
                },

                    object : CallbackCateg {
                        override fun onDataReadyC(data: List<CategoryTable>) {
                            _listcateg.postValue(data)
                        }

                    }

                )
            }

        }
        return listMov
    }


    fun updateMovie(): MutableLiveData<List<MovieTable>> {

        job?.cancel()
        job = CoroutineScope(Dispatchers.Main).launch(handler) {

            _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = true))
            val x = (1..3).random()
            if (x == 3) Integer.parseInt("one")
            movieRepo = MovieRepo()
            movieRepo.refreshData(getApplication(), object : CallbackMovie {
                override fun onDataReadyM(data: List<MovieTable>) {
                    var list: List<MovieTable> = data.shuffled()
                    listMov.value = list
                    _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
                }
            },
                object : CallbackCateg {
                    override fun onDataReadyC(data: List<CategoryTable>) {
                        _listcateg.postValue(data)
                    }

                }
            )
        }
        return listMov
    }

}

