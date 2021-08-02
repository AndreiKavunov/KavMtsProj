package ru.kavunov.mtsproject

import android.content.Context
import androidx.lifecycle.Observer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.*
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.mvvm.MvvmViewModelCateg
import ru.kavunov.mtsproject.mvvm.MvvmViewModelMovie
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


class ListFilmFragment : Fragment() {
    private val myViewModelCategorie: MvvmViewModelCateg by viewModels()
    private val myViewModelMovie: MvvmViewModelMovie by viewModels()

//    val bundle = Bundle()
    private var movieClickListener: MovieClickListener? = null
    private var adapterCateg= CategoryAdapter()
    private var adapterMovie= MovieAdapter()
    private var job: Job? = null
    val handler = CoroutineExceptionHandler { context, exception ->
        Log.d("tagErr","handled $exception")}
//    private val adapterMovie = MovieAdapter(listMov)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        val rcCateg = view.findViewById<RecyclerView>(R.id.RcCateg)
        val rcMovie = view.findViewById<RecyclerView>(R.id.RcMovie)

//        CoroutineScope(Dispatchers.Main).launch(handler) {
//
//                withContext(Dispatchers.IO){myViewModelMovie.loadMovie()}
//            withContext(Dispatchers.IO)
//            {myViewModelMovie.dataList.observe(requireActivity(), Observer(adapterMovie::initData))}
////            withContext(Dispatchers.IO){if(listMov.size < 1)newList()}
////            adapterMovie.changeList(listMov)
//
//        }

        myViewModelCategorie.loadCateg()
        myViewModelCategorie.categList.observe(requireActivity(), Observer(adapterCateg::initData))

    if( ChanList.listMov.size < 1) {
        myViewModelMovie.loadMovie()
        myViewModelMovie.dataList.observe(requireActivity(), Observer(adapterMovie::initData))
        myViewModelMovie.dataList.observe(requireActivity(), Observer(::changeList))
    }

        rcCateg.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcCateg.adapter = adapterCateg
        rcMovie.layoutManager = GridLayoutManager(getActivity(), 2)
        rcMovie.adapter = adapterMovie
        val indent_h = convertDpToPixels(requireActivity(), 150f)
        val dividerItemDecoration = CharacterItemDecoration(indent_h.toInt())
        rcMovie.addItemDecoration(dividerItemDecoration)

        val swipeToRefreshCentreal = view.findViewById<SwipeRefreshLayout>(R.id.swip)

        swipeToRefreshCentreal.setOnRefreshListener {
            job?.cancel()
            job = CoroutineScope(Dispatchers.Main).launch(handler) {
//                updateList()
                myViewModelMovie.updateList1()
                myViewModelMovie.dataList.observe(requireActivity(), Observer(adapterMovie::initData))
                myViewModelMovie.dataList.observe(requireActivity(), Observer(::changeList))
                val x = (1..3).random()
                if (x==5)Integer.parseInt("one")
//                adapterMovie.changeList(listMov)
            }

            swipeToRefreshCentreal.isRefreshing = false
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
//    suspend fun updateList() = withContext(Dispatchers.IO){
//        listMov.clear()
//        while (listMov.size < 6) {
//            var film = MoviesDataSourceImpl().getMovies().random()
//            if (film !in listMov)
//                listMov.add(film)        }
//        Thread.sleep(2000)}



//   fun newList(){
//        listMov.addAll(MoviesDataSourceImpl().getMovies())
//    }

    fun convertDpToPixels(context: Context, dp: Float) =
        dp * context.resources.displayMetrics.density

    fun changeList(movie: List<MovieDto>?){
        if (movie!=null){
            ChanList.listMov.clear()
            ChanList.listMov.addAll(movie)}
        Log.d("tag2", "qqq" + ChanList.listMov.toString())
    }

}
//

object ChanList {var listMov = ArrayList<MovieDto>()}
