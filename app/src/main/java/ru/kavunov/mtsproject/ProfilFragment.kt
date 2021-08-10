package ru.kavunov.mtsproject

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.adapter.CategoryAdapterNEW
import ru.kavunov.mtsproject.bd.CategoryModel
import ru.kavunov.mtsproject.bd.ProfListWithCateg
import ru.kavunov.mtsproject.bd.ProfilCateg
import ru.kavunov.mtsproject.bd.ProfilModel
import ru.kavunov.mtsproject.mvvm.ProfilViewModel
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl

class ProfilFragment : Fragment() {
    lateinit var profilViewModel: ProfilViewModel
var tTEST: List<ProfListWithCateg> = ArrayList()
    var listUser: MutableList<String> = ArrayList()
    private val adapterUser = CategoryAdapterNEW()
    lateinit var profil: ProfilModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val rcUser = view.findViewById<RecyclerView>(R.id.RcUserInter)
        val nameUser = view.findViewById<TextView>(R.id.nameUser)
        val emailUser = view.findViewById<TextView>(R.id.emailUser)
        val sNameUser = view.findViewById<TextView>(R.id.S_nameUser)
        val sPhone = view.findViewById<TextView>(R.id.s_phone)
        val sEmail = view.findViewById<TextView>(R.id.s_email)
        val img = view.findViewById<ImageView>(R.id.imageView)
        val button = view.findViewById<Button>(R.id.buttonExit)
        profilViewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)

        CoroutineScope(Dispatchers.Main).launch() {
            withContext(Dispatchers.IO){profilViewModel.getNameProfil(requireActivity(), "Иван")}
            profilViewModel.profil.observe(requireActivity(), Observer(::loadProf))

            profil = ProfilModel(1,"dddd", "dvdfvffv", "dddvvvv","https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg", "dddd", "dff", "dddd")

            rcUser.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
            rcUser.adapter = adapterUser
            listUser.add(profil.interests1)
            listUser.add(profil.interests2)
            listUser.add(profil.interests3)

            adapterUser.initData(listUser)
            nameUser.text = profil.name
            sPhone.text = profil.phone
            sNameUser.text = profil.name
            emailUser.text = profil.email
            sEmail.text = profil.email
            img.load(profil.foto)
            img.background = null
        }
        CoroutineScope(Dispatchers.Main).launch() {
            withContext(Dispatchers.IO){profilViewModel.getAllProfil(requireActivity())}
            withContext(Dispatchers.IO){profilViewModel.getAllCateg(requireActivity())}
            withContext(Dispatchers.IO){profilViewModel.getAllPrCt(requireActivity())}
            withContext(Dispatchers.IO){profilViewModel.getAllProfilTEST(requireActivity())}
//            Log.d("tag11", "prof.toString()")
            profilViewModel.listProfil.observe(requireActivity(), Observer(::log))
            profilViewModel.listCateg.observe(requireActivity(), Observer(::logCat))
            profilViewModel.listPrCt.observe(requireActivity(), Observer(::logPC))
            profilViewModel.listProfilT.observe(requireActivity(),{
                tTEST = it
                Log.d("tag11", "777" + tTEST.toString())
            })
        }
        button.setOnClickListener(){

//            profilViewModel.insertProfil(requireActivity(), id = 1, name = "Иван", email = "Andrei@mail.ru", phone = "8-909-000-9999",
//                foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
//                interests1 = "боевики",
//                interests2= "драмы",
//                interests3 = "комедии",)
//
//            val list = CategoryDataSourceImpl().getMovies()
//            for(x in list)
//                profilViewModel.insertCateg(requireContext(), id = 0, category = x.category)
//
//
//            profilViewModel.insertPrCt(requireContext(), idP = 1, idC = 2)
//            profilViewModel.insertPrCt(requireContext(), idP = 1, idC = 4)
//            profilViewModel.insertPrCt(requireContext(), idP = 1, idC = 5)


//            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
//            navController.popBackStack(R.id.listFilmFragment,true)
//            navController.navigate(R.id.listFilmFragment)



        }

        return view
    }
    fun loadProf(prof: ProfilModel?){
        if(prof!=null)
            profil = prof
    }
    fun log(profs: List<ProfilModel>?){
        if(profs != null){
            for(prof in profs)
                Log.d("tag11", prof.toString())
        }}

    fun logCat(list: List<CategoryModel>?){
        if(list != null){
            for(x in list)
                Log.d("tag11", x.toString())
        }}

    fun logPC(list: List<ProfilCateg>?){
        if(list != null){
            for(x in list)
                Log.d("tag11", x.toString())
        }}

}