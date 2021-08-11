package ru.kavunov.mtsproject.mvvm.repo

import ru.kavunov.mtsproject.bd.*



class ProfilRepo(val listM: List<ProfListWithCateg>, val profil: ProfilTableModel){
    fun refreshDataDet(onDataReadyCallback1: OnDataReadyCallbacProfil1, onDataReadyCallback2: OnDataReadyCallbacProfil2){
        var listAct: ArrayList<CategoryTableModel> = ArrayList()
        listAct.addAll(listM.getOrNull(0)?.listCat!!)

        onDataReadyCallback1.onDataReady1(listAct)
        onDataReadyCallback2.onDataReady2(profil)
    }
}
interface OnDataReadyCallbacProfil1 {
    fun onDataReady1(data: List<CategoryTableModel>)

}
interface OnDataReadyCallbacProfil2 {
    fun onDataReady2(data: ProfilTableModel)

}