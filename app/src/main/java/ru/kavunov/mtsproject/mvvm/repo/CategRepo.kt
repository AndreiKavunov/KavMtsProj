package ru.kavunov.mtsproject.mvvm


import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.mvvm.model.CategModel
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.CategResp


class CategRepo(){
    suspend fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallbackCateg){
        CoroutineScope(Dispatchers.Main).launch() {
            startBd(contetx)
            val list: List<CategoryTable>? = CategModel.getAll(contetx)
            if (list!=null)onDataReadyCallback.onDataReady(list)
        }}

}


interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<CategoryTable>)

}


//suspend fun getAllCat() : List<CategResp>? = withContext(Dispatchers.IO){
//    var categs: List<CategResp>
//    try {
//        categs = withContext(Dispatchers.IO) {
//            App.instance.apiService.getCateg().genres
//        }
//    } catch (e: Exception) {
//        categs = ArrayList()
//    }
//    return@withContext categs
//}

