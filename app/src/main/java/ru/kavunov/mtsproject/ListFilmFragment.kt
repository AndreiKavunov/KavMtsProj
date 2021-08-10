package ru.kavunov.mtsproject

import android.app.ProgressDialog
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
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.mvvm.MvvmViewModelCateg
import ru.kavunov.mtsproject.mvvm.MvvmViewModelMovie



class ListFilmFragment : Fragment() {

    private val myViewModelMovie: MvvmViewModelMovie by viewModels()
    private val myViewModelCateg: MvvmViewModelCateg by viewModels()
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
                myViewModelMovie.loadMovie()
                myViewModelCateg.loadCateg()
                myViewModelCateg.listcateg.observe(requireActivity(), Observer(adapterCateg::initData))
                myViewModelMovie.listmovie.observe(requireActivity(), Observer(adapterMovie::changeList))
                myViewModelMovie.viewState.observe(requireActivity(), Observer(::render))
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

                myViewModelMovie.listmovie.observe(requireActivity(), Observer(adapterMovie::changeList))
                myViewModelMovie.viewStateUp.observe(requireActivity(), Observer(::render2))
                myViewModelMovie.updateMovie()

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
    fun render2(viewState: ViewStateUpdete) = with(viewState) {
        swipeToRefreshCentreal.isRefreshing = isRefreshing

    }
//    private fun render(viewState: ViewState) = with(viewState) {
//        if (isDownloaded) {
//            progressDialog.show()
//        }
//        else {
//            progressDialog.dismiss()
//        }
//    }

}

object ListFilm {
    var listMov = ArrayList<MovieDto>()
    var listCat = ArrayList<Categorie>()
    var flag = 0
   }
