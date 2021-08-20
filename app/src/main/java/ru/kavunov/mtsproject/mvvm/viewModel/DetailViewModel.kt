package ru.kavunov.mtsproject.mvvm.viewModel
import android.app.Application

import android.util.Log

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.bd.ActorTable
import ru.kavunov.mtsproject.mvvm.*

//class ViewModelDetail: ViewModel() {
class DetailViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var detailRepo: DetailRepo

    val listDetail: LiveData<MovieDto> get() = _listDetail
    var _listDetail = MutableLiveData<MovieDto>()

    val listActors: LiveData<List<ActorTable>> get() = _listActors
    var _listActors = MutableLiveData<List<ActorTable>>()

    fun loadDetail(position: Long) {
        CoroutineScope(Dispatchers.Main).launch() {
            detailRepo = DetailRepo(position)

            detailRepo.refreshDataDet(object : OnCallbackMovD {

                override fun onDataMovD(data: MovieDto) {
                    _listDetail.postValue(data)
                }
            },
                object : OnCallbacActT {
                    override fun onDataActT(data: List<ActorTable>) {
                        _listActors.postValue(data)

                 }}

            )
        }
    }
}