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
    fun refreshData(onDataReadyCallback: OnDataReadyCallbackCateg){
        CoroutineScope(Dispatchers.Main).launch() {
            var list: ArrayList<CategoryTable>? = ArrayList()
            val listRep = getAllCat()
            if(listRep != null)for(i in listRep){
                list?.add(CategoryTable(categId = i.id.toLong(), category = i.name))
            }

            if (list!=null)onDataReadyCallback.onDataReady(list)
        }}

}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<CategoryTable>)

}

suspend fun getAllCat() : List<CategResp>? {
    var categs: List<CategResp>
    withContext(Dispatchers.IO){
    try {
        categs = withContext(Dispatchers.IO) {
            App.instance.apiService.getCateg().genres
        }
    } catch (e: Exception) {
        categs = ArrayList()
    }}
    return categs
}


