package ru.kavunov.mtsproject

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.kavunov.mtsproject.DTC.Categories
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl



class ListFilmFragment : Fragment() {
    private var changeDetails: ChangeDetails? = null

    val listCateg : List<Categories> = CategoryDataSourceImpl().getMovies()
    val listMov : List<MovieDto> = MoviesDataSourceImpl().getMovies()
    private val adapterCateg = CategoryAdapter(listCateg)

    lateinit var adapterMovie: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        val rcCateg = view.findViewById<RecyclerView>(R.id.RcCateg)
        val rcMovie = view.findViewById<RecyclerView>(R.id.RcMovie)
        adapterMovie = MovieAdapter(listMov, requireContext())
        rcCateg.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcCateg.adapter = adapterCateg
        rcMovie.layoutManager = GridLayoutManager(getActivity(), 2)
        rcMovie.adapter = adapterMovie

        val const = view.findViewById<TextView>(R.id.textView5)
        const.setOnClickListener{changeDetails?.clickDetail(1111111)}

       return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChangeDetails){
            changeDetails = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        changeDetails = null

    }


}