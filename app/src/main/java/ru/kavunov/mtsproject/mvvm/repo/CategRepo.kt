package ru.kavunov.mtsproject.mvvm


import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.mvvm.model.CategModel

//class CategRepo(val list: List<CategoryTable>){
class CategRepo(){
    fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallbackCateg){
        CoroutineScope(Dispatchers.Main).launch() {
        val list: List<CategoryTable>? = CategModel.getAll(contetx)
            if (list!=null)onDataReadyCallback.onDataReady(list)
    }}
}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<CategoryTable>)

}

