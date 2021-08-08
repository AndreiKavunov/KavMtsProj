package ru.kavunov.mtsproject.mvvm

import ru.kavunov.mtsproject.DTC.Categorie

class RepoCateg(val list: List<Categorie>){
   fun refreshData(onDataReadyCallback: OnDataReadyCallbackCateg){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<Categorie>)

}
