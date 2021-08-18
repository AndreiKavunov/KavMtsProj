package ru.kavunov.mtsproject.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Profil
import ru.kavunov.mtsproject.bd.*
import ru.kavunov.mtsproject.mvvm.repo.*


class ProfilViewModel(application: Application) : AndroidViewModel(application) {
//class ProfilViewModel : ViewModel() {

    lateinit var profilRepo: ProfilRepo

    val listProfil: LiveData<Profil> get() = _listProfil
    var _listProfil = MutableLiveData<Profil>()

    val listCateg: LiveData<List<CategoryTable>> get() = _listCateg
    var _listCateg = MutableLiveData<List<CategoryTable>>()

    fun loadDetail() {
        CoroutineScope(Dispatchers.Main).launch() {
            profilRepo = ProfilRepo()
            profilRepo.refreshDataDet(getApplication(), object : OnCallbacCategT {
                override fun onDataCatL(data: List<CategoryTable>) {
                    _listCateg.postValue(data)
                }
            },
                object : OnCallbacProf {
                    override fun onDataProf(data: Profil) {
                        _listProfil.postValue(data)
                    }
                }
            )
        }}
}
