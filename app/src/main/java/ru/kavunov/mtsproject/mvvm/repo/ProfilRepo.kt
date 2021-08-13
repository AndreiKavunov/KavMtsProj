package ru.kavunov.mtsproject.mvvm.repo

import ru.kavunov.mtsproject.DTC.Profil
import ru.kavunov.mtsproject.bd.*



class ProfilRepo(val listM: List<ProfListWithCateg>, val prof: ProfilTable){
    fun refreshDataDet(onDataReadyCallback1: OnDataReadyCallbacProfil1, onDataReadyCallback2: OnDataReadyCallbacProfil2){
        var listAct: ArrayList<CategoryTable> = ArrayList()
        listAct.addAll(listM.getOrNull(0)?.listCat!!)

        onDataReadyCallback1.onDataReady1(listAct)

        val profil = Profil(name = prof.name, email = prof.email, phone = prof.phone, foto = prof.foto)
        onDataReadyCallback2.onDataReady2(profil)
    }
}
interface OnDataReadyCallbacProfil1 {
    fun onDataReady1(data: List<CategoryTable>)

}
interface OnDataReadyCallbacProfil2 {
    fun onDataReady2(data: Profil)

}