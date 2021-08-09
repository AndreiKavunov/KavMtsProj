package ru.kavunov.mtsproject.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.ProfilModel


class ProfilViewModel : ViewModel() {


    val listProfil: LiveData<List<ProfilModel>> get() = _listProfil
    var _listProfil = MutableLiveData<List<ProfilModel>>()

    val profil: LiveData<ProfilModel> get() = _profil
    var _profil = MutableLiveData<ProfilModel>()


    fun insertData(context: Context, name: String, email: String, phone: String, foto: String,
                   interests1: String, interests2: String, interests3: String) {
        ProfilRepos.insertData(context, name, email, phone, foto, interests1, interests2, interests3)
    }

    fun getAll(context: Context){
        var aaa = ProfilRepos.getAll(context)
        _listProfil.postValue(aaa)
    }

    fun getName(context: Context, name: String){
        var aaa = ProfilRepos.getLoginDetails(context, name)
        _profil.postValue(aaa)
    }

}
