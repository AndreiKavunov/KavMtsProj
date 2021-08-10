package ru.kavunov.mtsproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.ListFilm
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import java.util.*

class MvvmViewModelCateg: ViewModel() {

    lateinit var categoryModel: RepoCateg


    val listcateg: LiveData<List<Categorie>> get() = _listcateg
    var _listcateg = MutableLiveData<List<Categorie>>()

fun loadCateg(){
    CoroutineScope(Dispatchers.Main).launch() {
    categoryModel= RepoCateg(CategoryDataSourceImpl().getMovies())
    categoryModel.refreshData( object: OnDataReadyCallbackCateg{
        override fun onDataReady(data: List<Categorie>) {
            _listcateg.postValue(data)
            changeListF(data)
        }
    }
    )}}

    fun changeListF(categ: List<Categorie>){
        ListFilm.listCat.clear()
        ListFilm.listCat.addAll(categ)

    }
}