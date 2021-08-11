package ru.kavunov.mtsproject.mvvm


import ru.kavunov.mtsproject.bd.CategoryTableModel

class CategRepo(val list: List<CategoryTableModel>){
    fun refreshData(onDataReadyCallback: OnDataReadyCallbackCateg){

        onDataReadyCallback.onDataReady(list)
    }
}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<CategoryTableModel>)

}

