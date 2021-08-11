package ru.kavunov.mtsproject.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.ListFilm
import ru.kavunov.mtsproject.bd.CategoryTableModel
import ru.kavunov.mtsproject.mvvm.CategRepo
import ru.kavunov.mtsproject.mvvm.OnDataReadyCallbackCateg
import ru.kavunov.mtsproject.mvvm.model.CategModel

//class MvvmViewModelCateg: ViewModel() {
class CategViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var categoryModel: CategRepo


    val listcateg: LiveData<List<CategoryTableModel>> get() = _listcateg
    var _listcateg = MutableLiveData<List<CategoryTableModel>>()

    fun loadCateg(){
        CoroutineScope(Dispatchers.Main).launch() {
            categoryModel= CategRepo(CategModel.getAll(getApplication())!!)
            categoryModel.refreshData( object : OnDataReadyCallbackCateg {
                override fun onDataReady(data: List<CategoryTableModel>) {
                    _listcateg.postValue(data)
                    changeListF(data)
                }

            }
            )}}

    fun changeListF(categ: List<CategoryTableModel>){
        ListFilm.listCat.clear()
        ListFilm.listCat.addAll(categ)
    }
}
