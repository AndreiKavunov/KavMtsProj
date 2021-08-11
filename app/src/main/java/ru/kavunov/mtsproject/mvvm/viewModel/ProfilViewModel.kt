package ru.kavunov.mtsproject.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.*
import ru.kavunov.mtsproject.mvvm.model.ProfilModel
import ru.kavunov.mtsproject.mvvm.repo.*


class ProfilViewModel(application: Application) : AndroidViewModel(application) {
//class ProfilViewModel : ViewModel() {

    lateinit var profilRepo: ProfilRepo

    val listProfil: LiveData<ProfilTableModel> get() = _listProfil
    var _listProfil = MutableLiveData<ProfilTableModel>()

    val listCateg: LiveData<List<CategoryTableModel>> get() = _listCateg
    var _listCateg = MutableLiveData<List<CategoryTableModel>>()

    fun loadDetail1(id: Long) {
        CoroutineScope(Dispatchers.Main).launch() {
            profilRepo = ProfilRepo(ProfilModel.getCategList(getApplication(), 1L)!!,
                ProfilModel.getProfilId(getApplication(), id)!!)
            profilRepo.refreshDataDet(object : OnDataReadyCallbacProfil1 {
                override fun onDataReady1(data: List<CategoryTableModel>) {
                    _listCateg.postValue(data)
                }
            },
                object : OnDataReadyCallbacProfil2 {
                    override fun onDataReady2(data: ProfilTableModel) {
                        _listProfil.postValue(data)
                    }
                }
            )
        }}
}
