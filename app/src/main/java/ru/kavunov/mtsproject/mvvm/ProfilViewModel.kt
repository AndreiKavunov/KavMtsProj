package ru.kavunov.mtsproject.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.*


class ProfilViewModel : ViewModel() {

    val listProfilT: LiveData<List<ProfListWithCateg>> get() = _listProfilT
    var _listProfilT = MutableLiveData<List<ProfListWithCateg>>()

    val listProfilTT: LiveData<List<MovListWithAct>> get() = _listProfilTT
    var _listProfilTT = MutableLiveData<List<MovListWithAct>>()

    val listProfilM: LiveData<List<MovieModel>> get() = _listProfilM
    var _listProfilM = MutableLiveData<List<MovieModel>>()

    val listProfil: LiveData<List<ProfilModel>> get() = _listProfil
    var _listProfil = MutableLiveData<List<ProfilModel>>()

    val listCateg: LiveData<List<CategoryModel>> get() = _listCateg
    var _listCateg = MutableLiveData<List<CategoryModel>>()

    val listPrCt: LiveData<List<ProfilCateg>> get() = _listPrCt
    var _listPrCt = MutableLiveData<List<ProfilCateg>>()

    val profil: LiveData<ProfilModel> get() = _profil
    var _profil = MutableLiveData<ProfilModel>()


    fun insertProfil(context: Context, id: Long, name: String, email: String, phone: String, foto: String,) {
        ProfilRepos.insertData(context, id, name, email, phone, foto)
    }

    fun getAllMovie(context: Context){
        var aaa = MovieRepos.getAll(context)
        _listProfilM.postValue(aaa)
    }

    fun getAllProfil(context: Context){
        var aaa = ProfilRepos.getAll(context)
        _listProfil.postValue(aaa)
    }

    fun getNameProfil(context: Context, name: String){
        var aaa = ProfilRepos.getLoginDetails(context, name)
        _profil.postValue(aaa)
    }

    fun getAllProfilTEST(context: Context){
        var aaa = ProfilRepos.getTEST(context)
        _listProfilT.postValue(aaa)
    }

    fun getAllMovieTEST(context: Context){
        var aaa = MovieRepos.getTEST(context)
        _listProfilTT.postValue(aaa)
    }


    fun insertCateg(context: Context, id: Long, category: String) {
        CategRepos.insertData(context, id, category)
    }
    fun getAllCateg(context: Context){
        var aaa = CategRepos.getAll(context)
        _listCateg.postValue(aaa)
    }

    fun insertPrCt(context: Context, idP: Long, idC: Long) {
        ProfilCatRepos.insertData(context, idP, idC)
    }

    fun getAllPrCt(context: Context){
        var aaa = ProfilCatRepos.getAll(context)
        _listPrCt.postValue(aaa)
    }

}
