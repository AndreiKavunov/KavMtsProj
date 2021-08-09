package ru.kavunov.mtsproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.CategoryAdapterNEW
import ru.kavunov.mtsproject.bd.ProfilModel
import ru.kavunov.mtsproject.mvvm.ProfilViewModel
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryUser
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import coil.load

class ProfilFragment : Fragment() {
    lateinit var profilViewModel: ProfilViewModel

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
            withContext(Dispatchers.IO){profilViewModel.getName(requireActivity(), "Иван")}
            profilViewModel.profil.observe(requireActivity(), Observer(::loadProf))
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
            withContext(Dispatchers.IO){profilViewModel.getAll(requireActivity())}
            profilViewModel.listProfil.observe(requireActivity(), Observer(::log))
        }
        button.setOnClickListener(){
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.listFilmFragment)
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
}