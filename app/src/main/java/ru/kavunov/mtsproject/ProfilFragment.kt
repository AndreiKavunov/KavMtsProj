package ru.kavunov.mtsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.DTC.Categories
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryUser


class ProfilFragment : Fragment() {
    val listUser : List<Categories> = CategoryUser().getMovies()
    private val adapterUser = CategoryAdapter(listUser)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profil, container, false)
        val rcUser = view.findViewById<RecyclerView>(R.id.RcUserInter)
        rcUser.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcUser.adapter = adapterUser

        return view
    }

}