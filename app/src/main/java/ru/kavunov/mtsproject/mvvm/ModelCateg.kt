package ru.kavunov.mtsproject.mvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Categorie

class RepoCateg(val list: List<Categorie>){
   suspend fun refreshData(onDataReadyCallback: OnDataReadyCallbackCateg)= withContext(Dispatchers.IO){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<Categorie>)

}
