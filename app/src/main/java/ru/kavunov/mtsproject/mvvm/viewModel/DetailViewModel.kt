package ru.kavunov.mtsproject.mvvm.viewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.ActorTableModel
import ru.kavunov.mtsproject.bd.MovieTableModel
import ru.kavunov.mtsproject.mvvm.*
import ru.kavunov.mtsproject.mvvm.model.MovieModel

//class ViewModelDetail: ViewModel() {
class DetailViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var movieDetailRepo: DetailRepo

    val listDetail: LiveData<MovieTableModel> get() = _listDetail
    var _listDetail = MutableLiveData<MovieTableModel>()

    val listActors: LiveData<List<ActorTableModel>> get() = _listActors
    var _listActors = MutableLiveData<List<ActorTableModel>>()

    fun loadDetail(position: Long) {
        CoroutineScope(Dispatchers.Main).launch() {
            movieDetailRepo = DetailRepo(MovieModel.getMovieID(getApplication(), position)!!, MovieModel.getActorList(getApplication(), position)!!)
            movieDetailRepo.refreshDataDet(object : OnDataReadyCallbackDetail1 {
                override fun onDataReady1(data: MovieTableModel) {
                    _listDetail.postValue(data)
                }
            },
                object : OnDataReadyCallbackDetail2 {
                    override fun onDataReady2(data: List<ActorTableModel>) {
                        _listActors.postValue(data)
                    }}
            )
        }
    }
}