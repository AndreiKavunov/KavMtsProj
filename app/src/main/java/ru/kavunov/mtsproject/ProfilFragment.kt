package ru.kavunov.mtsproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.DTC.Profil
import ru.kavunov.mtsproject.adapter.CategoryAdapterNEW
import ru.kavunov.mtsproject.mvvm.MovieRepo1
import ru.kavunov.mtsproject.mvvm.changeBd
import ru.kavunov.mtsproject.mvvm.viewModel.ProfilViewModel

class ProfilFragment : Fragment() {


    private val profilViewModel: ProfilViewModel by viewModels()
    private val adapterUser = CategoryAdapterNEW()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val rcUser = view.findViewById<RecyclerView>(R.id.RcUserInter)
        val button = view.findViewById<Button>(R.id.buttonExit)

        profilViewModel.loadDetail()
        profilViewModel.listProfil.observe(requireActivity(), Observer(::viewProf))
        profilViewModel.listCateg.observe(requireActivity(), Observer(adapterUser::initData))
        rcUser.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcUser.adapter = adapterUser


        button.setOnClickListener(){
            CoroutineScope(Dispatchers.Main).launch() {
            fragListFilmF()
            changeBd(requireActivity())
        }}


        return view
            }

    fun viewProf(profil: Profil){
        view?.findViewById<TextView>(R.id.nameUser)?.text = profil.name
        view?.findViewById<TextView>(R.id.emailUser)?.text = profil.email
        view?.findViewById<TextView>(R.id.S_nameUser)?.text = profil.name
        view?.findViewById<TextView>(R.id.s_phone)?.text = profil.phone
        view?.findViewById<TextView>(R.id.s_email)?.text = profil.email
        view?.findViewById<ImageView>(R.id.imageView)?.load(profil.foto)
        view?.findViewById<ImageView>(R.id.imageView)?.background = null
    }

    fun fragListFilmF(){
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.popBackStack(R.id.listFilmFragment,true)
        navController.navigate(R.id.listFilmFragment)
    }

}