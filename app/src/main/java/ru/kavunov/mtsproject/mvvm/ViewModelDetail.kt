package ru.kavunov.mtsproject.mvvm
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class ViewModelDetail: ViewModel() {
    lateinit var movieDetail: RepoDetail

    val listDetail: LiveData<MovieDto> get() = _listDetail
    var _listDetail = MutableLiveData<MovieDto>()

    val listActors: LiveData<List<Actors>> get() = _listActors
    var _listActors = MutableLiveData<List<Actors>>()

    fun loadDetail(position: Int) {
        CoroutineScope(Dispatchers.Main).launch() {
            movieDetail = RepoDetail(ListFilm.listMov)
            movieDetail.refreshDataDet(object : OnDataReadyCallbackDetail {
                override fun onDataReady2(data: ArrayList<MovieDto>) {
                    _listDetail.postValue(ListFilm.listMov[position])
                    _listActors.postValue(ListFilm.listMov[position].actor)
                }
            }
            )
        }

    }

}