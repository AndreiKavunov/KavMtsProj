package ru.kavunov.mtsproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


class ListFilmFragment : Fragment() {
    private var movieClickListener: MovieClickListener? = null
    val listCateg : List<Categorie> = CategoryDataSourceImpl().getMovies()
    private val adapterCateg = CategoryAdapter(listCateg)
    private var job: Job? = null
    private val adapterMovie = MovieAdapter(ListFilm.listMov)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        val rcCateg = view.findViewById<RecyclerView>(R.id.RcCateg)
        val rcMovie = view.findViewById<RecyclerView>(R.id.RcMovie)
        if(ListFilm.listMov.size < 1){
        CoroutineScope(Dispatchers.Main).launch() {
        changeListF(ListFilm.flag)
        adapterMovie.changeList(ListFilm.listMov)
        }}

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
                changeListF(ListFilm.flag)
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

//    suspend fun changeListF1(flaf: Int): List<MovieDto> = withContext(Dispatchers.IO){
//        val list = MoviesDataSourceImpl().getMovies()[flaf]
//        return@withContext list
//    }
    suspend fun changeListF(flaf: Int) = withContext(Dispatchers.IO){
        ListFilm.listMov.clear()
        ListFilm.listMov.addAll(MoviesDataSourceImpl().getMovies()[flaf])

    }


}
object ListFilm {
    var listMov = ArrayList<MovieDto>()
    var flag = 0
}