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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.DTC.Actors
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.ActorsAdapter
import ru.mts.teta.summer.android.homework.list.data.features.movies.ActorsDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


class DetailkFragment : Fragment() {
    private lateinit var moviesModel: MoviesModel
    val listActor : List<List<Actors>> = ActorsDataSourceImpl().getMoviesA()
    lateinit var adapterActors: ActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_detailk, container, false)
        val rcActors = view.findViewById<RecyclerView>(R.id.RcActor)
        initDataSource()
        var listfilm = getMovieAt(arguments?.getInt(ARG_PARAM1)!!)
        adapterActors = ActorsAdapter(listfilm!!.actor)
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
        val movies = moviesModel.getMovies()
        Log.d("tag1", "$movies")
        return when {
            movies.isEmpty() -> null
            position >= movies.size -> null
            else -> movies[position]
        }
    }
    private fun initDataSource() {
        moviesModel = MoviesModel(MoviesDataSourceImpl())

    }


    companion object {
        const val ARG_PARAM1 = "param1"
        @JvmStatic
        fun newInstance(param1: Int) =
            DetailkFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)

                }
            }
    }
}