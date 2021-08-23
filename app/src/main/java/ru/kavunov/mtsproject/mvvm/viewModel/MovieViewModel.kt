package ru.kavunov.mtsproject.mvvm.viewModel


import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.*
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.*
import ru.kavunov.mtsproject.mvvm.model.*
import ru.kavunov.mtsproject.recponse.IMG_HEADER
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl


typealias MyViewState = ListFilmFragment.ViewState
typealias MyViewStateUpdate = ListFilmFragment.ViewStateUpdete
class MovieViewModel(application: Application) : AndroidViewModel(application) {
//    class MovieViewModel: ViewModel() {

    private var job: Job? = null
    lateinit var movieRepo: MovieRepo
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
            startBd(contextM)
            if (listMov.value == null) {
                Log.d("tag11", listMov.value.toString())
                movieRepo = MovieRepo()
                movieRepo.refreshData(getApplication(), object : OnDataReadyCallback {
                    override fun onDataReady(data: List<MovieTable>) {
                        listMov.value = data
                        _viewState.postValue(MyViewState(isDownloaded = false))
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
            movieRepo.refreshData(getApplication(), object : OnDataReadyCallback {
                override fun onDataReady(data: List<MovieTable>) {
                    var list: List<MovieTable> = data.shuffled()
                    listMov.value = list
                    _viewStateUp.postValue(MyViewStateUpdate(isRefreshing = false))
                }
            }
            )
        }
        return listMov
    }

}
fun startBd(context: Context){
    CoroutineScope(Dispatchers.Main).launch() {
        if(MovieModel.getAll(context)?.size == 0){
            ProfilCatModel.insertData(context, 1, 28)
            ProfilCatModel.insertData(context, 1, 12)
            ProfilCatModel.insertData(context, 1, 16)
            ProfilModel.insertData(
                context,
                id = 1,
                name = "Иван",
                email = "Ivan@mail.ru",
                phone = "8-909-000-9999",
                foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
            )

            val listCateg = getAllCat()
            Log.d("tag11", "555" +listCateg.toString())
            if (listCateg != null) {
                for(i in listCateg) {
                    CategModel.insertData(context, i.id.toLong(), i.name)
                }

            }
            val listMov = getAllMov()
            if (listMov != null) {
                var idF = 0L
                var idA = 0L
                for(i in listMov){
                    idF = i.id.toLong()
                    var age = getAllA(i.id.toString())
                    var genre = CategModel.getLoginDetails(context, i.genreIds[0].toLong())!!.category
                    val listActor = getAllActors(i.id.toString())
                    MovieModel.insertData(context= context, id= i.id.toLong(), title = i.title, description= i.overview,
                    imageUrl = IMG_HEADER + i.posterPath, ageRestriction = age, rateScore = i.voteAverage/2,
                        releaseDate= i.releaseDate, genre = genre)

                    if (listActor != null) {
                        for (actor in listActor.take(5)){
                            idA = actor.id.toLong()
                            ActorModel.insertData(context = context, id = actor.id.toLong(),
                            imgAct = IMG_HEADER + actor.profilePath, nameAct = actor.name)
                            MovieActModel.insertData(context, idF, idA)

                        }


                    }
                }}

}
    }
}

