package ru.kavunov.mtsproject

import android.content.Context
import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.mvvm.MvvmViewModelCateg
import ru.kavunov.mtsproject.mvvm.MvvmViewModelMovie


class ListFilmFragment : Fragment() {
    private val myViewModelCategorie: MvvmViewModelCateg by viewModels()
    private val myViewModelMovie: MvvmViewModelMovie by viewModels()

    private var movieClickListener: MovieClickListener? = null
    private var adapterCateg= CategoryAdapter()

    private var adapterMovie= MovieAdapter()
    private var job: Job? = null

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        val rcCateg = view.findViewById<RecyclerView>(R.id.RcCateg)
        val rcMovie = view.findViewById<RecyclerView>(R.id.RcMovie)

        Log.d("tag25", ListFilm.listMov.toString())
        if(ListFilm.listMov.size < 1){
            CoroutineScope(Dispatchers.Main).launch() {
//                changeListF()
                myViewModelMovie.refresh()
                myViewModelMovie.text.observe(requireActivity(), Observer(::changeListF))
                adapterMovie.changeList(ListFilm.listMov)
            }}
        else adapterMovie.changeList(ListFilm.listMov)

        myViewModelCategorie.loadCateg()
        myViewModelCategorie.categList.observe(requireActivity(), Observer(adapterCateg::initData))

        rcCateg.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcCateg.adapter = adapterCateg
        rcMovie.layoutManager = GridLayoutManager(getActivity(), 2)
        rcMovie.adapter = adapterMovie
        val indent_h = convertDpToPixels(requireActivity(), 150f)
        val dividerItemDecoration = CharacterItemDecoration(indent_h.toInt())
        rcMovie.addItemDecoration(dividerItemDecoration)

        val swipeToRefreshCentreal = view.findViewById<SwipeRefreshLayout>(R.id.swip)

        val handler = CoroutineExceptionHandler { context, exception ->
            Log.d("tagErr","handled $exception")
            Toast.makeText(requireActivity(), "Не удалось получить данные, повторите попытку.", Toast.LENGTH_SHORT).show()
            swipeToRefreshCentreal.isRefreshing = false
        }
        swipeToRefreshCentreal.setOnRefreshListener {
            job?.cancel()
            job = CoroutineScope(Dispatchers.Main).launch(handler) {
                val x = (1..3).random()
                if (x==1)Integer.parseInt("one")
                if(ListFilm.flag == 0) ListFilm.flag = 1 else ListFilm.flag = 0
//                myViewModelMovie.loadMovie()
//                myViewModelMovie.dataList.observe(requireActivity(), Observer(::changeListF))

                myViewModelMovie.refresh()
                myViewModelMovie.text.observe(requireActivity(), Observer(::changeListF))

//                Log.d("tag11", "WWW" + ListFilm.x)
                adapterMovie.changeList(ListFilm.listMov)
                swipeToRefreshCentreal.isRefreshing = false
            }
        }

        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieClickListener){
            movieClickListener = context
        }

    }

    override fun onDetach() {
        super.onDetach()
        movieClickListener = null

    }

    fun convertDpToPixels(context: Context, dp: Float) =
        dp * context.resources.displayMetrics.density

    fun changeListF(movie: List<MovieDto>?){
        Log.d("tag11", "HHH" + movie.toString())
        if (movie!=null){
            ListFilm.listMov.clear()
            ListFilm.listMov.addAll(movie)}

    }
    fun xxx(a: String){
        ListFilm.x = a
        Log.d("tag11", "HHH" + ListFilm.x)
    }
}

object ListFilm {
    var listMov = ArrayList<MovieDto>()
    var flag = 0
    var x = "sdss"}
