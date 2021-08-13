package ru.kavunov.mtsproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.adapter.ActorsAdapter
import androidx.lifecycle.Observer
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.mvvm.viewModel.DetailViewModel


class DetailkFragment : Fragment() {
    private val detailViewModel: DetailViewModel by viewModels()
    var adapterActors= ActorsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = arguments?.getString("MyArg")
        val view =  inflater.inflate(R.layout.fragment_detailk, container, false)
        val rcActors = view.findViewById<RecyclerView>(R.id.RcActor)

        detailViewModel.loadDetail(position!!.toLong())
        detailViewModel.listDetail.observe(requireActivity(), Observer(::viewMovie))
        detailViewModel.listActors.observe(requireActivity(), Observer(adapterActors::initData))
        rcActors.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcActors.adapter = adapterActors

        return view
    }

    fun viewMovie(movieDto: MovieDto){
        view?.findViewById<TextView>(R.id.titleId)?.text = movieDto.title
        view?.findViewById<TextView>(R.id.descripId)?.text = movieDto.description
        view?.findViewById<RatingBar>(R.id.filmRatingDet)?.rating = (movieDto.rateScore?.toFloat() ?: 0.0) as Float
        view?.findViewById<TextView>(R.id.ageRestrictionId)?.text = movieDto.ageRestriction.toString() + "+"
        view?.findViewById<ImageView>(R.id.imageDetId)?.load(movieDto.imageUrl)
    }
}