package ru.kavunov.mtsproject.mvvm.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.mvvm.CategRepo
import ru.kavunov.mtsproject.mvvm.OnDataReadyCallbackCateg


//class MvvmViewModelCateg: ViewModel() {
class CategViewModel(application: Application) : AndroidViewModel(application) {

    lateinit var categoryRepo: CategRepo


    val listcateg: LiveData<List<CategoryTable>> get() = _listcateg
    var _listcateg = MutableLiveData<List<CategoryTable>>()

    fun loadCateg(){
        CoroutineScope(Dispatchers.Main).launch() {
            categoryRepo= CategRepo()

            categoryRepo.refreshData(object : OnDataReadyCallbackCateg {

                override fun onDataReady(data: List<CategoryTable>) {
                    _listcateg.postValue(data)

                }

            }
            )}}


}
