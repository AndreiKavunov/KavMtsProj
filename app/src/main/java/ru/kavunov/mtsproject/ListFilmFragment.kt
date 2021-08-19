package ru.kavunov.mtsproject
import android.app.ProgressDialog
import android.content.Context
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.DTC.MovieResponse
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.bd.CategoryTable
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.viewModel.CategViewModel

import ru.kavunov.mtsproject.mvvm.viewModel.MovieViewModel


class ListFilmFragment : Fragment() {

    private val myViewModelMovieViewModel: MovieViewModel by viewModels()
    private val myViewModelCategViewModel: CategViewModel by viewModels()
    private var movieClickListener: MovieClickListener? = null
    private var adapterCateg= CategoryAdapter()
    private val progressDialog by lazy { ProgressDialog.show(requireActivity(), "", getString(R.string.please_wait)) }
    private var adapterMovie= MovieAdapter()
    lateinit var swipeToRefreshCentreal : SwipeRefreshLayout

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list_film, container, false)
        val rcCateg = view.findViewById<RecyclerView>(R.id.RcCateg)
        val rcMovie = view.findViewById<RecyclerView>(R.id.RcMovie)

        if(ListFilm.listMov.size < 1){
            progressDialog.show()
            myViewModelMovieViewModel.loadMovie()
            myViewModelCategViewModel.loadCateg()
            myViewModelCategViewModel.listcateg.observe(requireActivity(), Observer(adapterCateg::initData))
            myViewModelMovieViewModel.listmovie.observe(requireActivity(), Observer(adapterMovie::changeList))
            myViewModelMovieViewModel.viewState.observe(requireActivity(), Observer(::render))
        }
        else {adapterMovie.changeList(ListFilm.listMov)
            adapterCateg.initData(ListFilm.listCat)
        }

        rcCateg.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcCateg.adapter = adapterCateg
        rcMovie.layoutManager = GridLayoutManager(getActivity(), 2)
        rcMovie.adapter = adapterMovie

        val indent_h = convertDpToPixels(requireActivity(), 150f)
        val dividerItemDecoration = CharacterItemDecoration(indent_h.toInt())
        rcMovie.addItemDecoration(dividerItemDecoration)

        swipeToRefreshCentreal = view.findViewById(R.id.swip)
        swipeToRefreshCentreal.setOnRefreshListener {

            myViewModelMovieViewModel.listmovie.observe(requireActivity(), Observer(adapterMovie::changeList))
            myViewModelMovieViewModel.viewStateUp.observe(requireActivity(), Observer(::renderSwipe))
            myViewModelMovieViewModel.updateMovie()

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

    data class ViewState(
        var isDownloaded: Boolean = false
    )
    data class ViewStateUpdete(
        var isRefreshing: Boolean = false
    )
    private fun render(viewState: ViewState){
        progressDialog.dismiss()
    }
    fun renderSwipe(viewState: ViewStateUpdete) = with(viewState) {
        swipeToRefreshCentreal.isRefreshing = isRefreshing

    }

}

object ListFilm {
    var listMov = ArrayList<MovieTable>()
    var listMovForDetail = ArrayList<MovieDto>()
    var listCat = ArrayList<CategoryTable>()
    var idFilm = "436969"
    var idFilmAge = "436969"
}

