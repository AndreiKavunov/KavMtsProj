package ru.kavunov.mtsproject.mvvm.repo

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Profil
import ru.kavunov.mtsproject.bd.*
import ru.kavunov.mtsproject.mvvm.model.ProfilModel


class ProfilRepo(){
    fun refreshDataDet(context: Context, onCallbacCategT: OnCallbacCategT,
                       onCallbacProf: OnCallbacProf){
        CoroutineScope(Dispatchers.Main).launch() {
        val listM: List<ProfListWithCateg>? = ProfilModel.getCategList(context, 1L)

        val prof: ProfilTable? =  ProfilModel.getProfilId(context, 1L)
        var listAct: ArrayList<CategoryTable> = ArrayList()
        listM?.getOrNull(0)?.listCat?.let { listAct.addAll(it) }

        onCallbacCategT.onDataCatL(listAct)

        val profil =
            prof?.name?.let { Profil(name = it, email = prof.email, phone = prof.phone, foto = prof.foto) }
            if (profil != null) {
                onCallbacProf.onDataProf(profil)
            }
    }}
}
interface OnCallbacCategT {
    fun onDataCatL(data: List<CategoryTable>)

}
interface OnCallbacProf {
    fun onDataProf(data: Profil)

}