package ru.kavunov.mtsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryUser


class ProfilFragment : Fragment() {
    val listUser : List<Categorie> = CategoryUser().getMovies()

    private val adapterUser = CategoryAdapter()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val rcUser = view.findViewById<RecyclerView>(R.id.RcUserInter)
        rcUser.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcUser.adapter = adapterUser

        adapterUser.initData(listUser)


        return view
    }

}