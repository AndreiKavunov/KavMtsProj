package ru.kavunov.mtsproject.mvvm.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.*
import ru.kavunov.mtsproject.mvvm.model.MovieModel

//class ViewModelDetail: ViewModel() {
class DetailViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var detailRepo: DetailRepo

    val listDetail: LiveData<MovieTable> get() = _listDetail
    var _listDetail = MutableLiveData<MovieTable>()

    val listActors: LiveData<List<ActorTable>> get() = _listActors
    var _listActors = MutableLiveData<List<ActorTable>>()

    fun loadDetail(position: Long) {
        CoroutineScope(Dispatchers.Main).launch() {
            detailRepo = DetailRepo(MovieModel.getMovieID(getApplication(), position)!!, MovieModel.getActorList(getApplication(), position)!!)
            detailRepo.refreshDataDet(object : OnDataReadyCallbackDetail1 {
                override fun onDataReady1(data: MovieTable) {
                    _listDetail.postValue(data)
                }
            },
                object : OnDataReadyCallbackDetail2 {
                    override fun onDataReady2(data: List<ActorTable>) {
                        _listActors.postValue(data)
                    }}
            )
        }
    }
}