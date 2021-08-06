package ru.kavunov.mtsproject

import android.os.Bundle
import android.util.Log
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
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.ActorsAdapter
import ru.kavunov.mtsproject.mvvm.MvvmViewModelMovie
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
import androidx.lifecycle.Observer


class DetailkFragment : Fragment() {

    var adapterActors= ActorsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = arguments?.getString("MyArg")
        val view =  inflater.inflate(R.layout.fragment_detailk, container, false)
        val rcActors = view.findViewById<RecyclerView>(R.id.RcActor)
        var listfilm = getMovieAt(position!!.toInt())
        adapterActors.initData(listfilm?.actor)

        rcActors.layoutManager = LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcActors.adapter = adapterActors
        view.findViewById<TextView>(R.id.titleId).text = listfilm?.title
        view.findViewById<TextView>(R.id.descripId).text = listfilm?.description
        view.findViewById<RatingBar>(R.id.filmRatingDet).rating = (listfilm?.rateScore?.toFloat() ?: 0.0) as Float
        view.findViewById<TextView>(R.id.ageRestrictionId).text = listfilm?.ageRestriction.toString() + "+"
        view.findViewById<ImageView>(R.id.imageDetId).load(listfilm?.imageUrl)
        return view
    }
    private fun getMovieAt(position: Int = 1): MovieDto? {
        val movies = ListFilm.listMov
           return when {
            movies.isEmpty() -> null
            position >= movies.size -> null
            else -> movies[position]
        }
    }

}