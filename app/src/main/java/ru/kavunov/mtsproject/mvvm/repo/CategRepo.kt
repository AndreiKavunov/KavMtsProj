package ru.kavunov.mtsproject.mvvm


import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.mvvm.model.CategModel

import ru.kavunov.mtsproject.recponse.respModel.CategRecpModel


//class CategRepo(){
//    fun refreshData(contetx: Context, onDataReadyCallback: OnDataReadyCallbackCateg){
//        CoroutineScope(Dispatchers.Main).launch() {
//        val list: List<CategoryTable>? = CategModel.getAll(contetx)
//            if (list!=null)onDataReadyCallback.onDataReady(list)
//    }}
//}

class CategRepo(){
    fun refreshData(onDataReadyCallback: OnDataReadyCallbackCateg){
        CoroutineScope(Dispatchers.Main).launch() {
            var list: ArrayList<CategoryTable>? = ArrayList()
            val listRep = CategRecpModel.getAll()
            if(listRep != null)for(i in listRep){
                list?.add(CategoryTable(categId = i.id.toLong(), category = i.name))
            }

            if (list!=null)onDataReadyCallback.onDataReady(list)
        }}

}
interface OnDataReadyCallbackCateg {
    fun onDataReady(data: List<CategoryTable>)

}

