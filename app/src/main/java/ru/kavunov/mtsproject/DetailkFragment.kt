package ru.kavunov.mtsproject

import android.os.Bundle
import android.transition.ChangeBounds
import androidx.transition.Fade
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
import androidx.transition.TransitionInflater
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.mvvm.viewModel.DetailViewModel


class DetailkFragment : Fragment() {
    private val detailViewModel: DetailViewModel by viewModels()
    var adapterActors = ActorsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move).apply {
                duration = 2000
            }

        enterTransition = Fade()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val position = arguments?.getString("MyArg")
        val view = inflater.inflate(R.layout.fragment_detailk, container, false)
        val rcActors = view.findViewById<RecyclerView>(R.id.RcActor)
        view.findViewById<View>(R.id.titleId)?.transitionName = position
        view.findViewById<View>(R.id.imageDetId)?.transitionName = "image" + position
        if (position != null) detailViewModel.loadDetail(position.toLong())
        detailViewModel.listDetail.observe(requireActivity(), Observer(::viewMovie))
        detailViewModel.listActors.observe(requireActivity(), Observer(adapterActors::initData))
        rcActors.layoutManager =
            LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false)
        rcActors.adapter = adapterActors

        return view
    }

    fun viewMovie(movieDto: MovieDto) {
        view?.findViewById<TextView>(R.id.titleId)?.text = movieDto.title

        view?.findViewById<TextView>(R.id.textgenre)?.text = movieDto.genre
        view?.findViewById<TextView>(R.id.textData)?.text = movieDto.releaseDate
        view?.findViewById<TextView>(R.id.descripId)?.text = movieDto.description
        view?.findViewById<RatingBar>(R.id.filmRatingDet)?.rating =
            (movieDto.rateScore?.toFloat() ?: 0.0) as Float
        view?.findViewById<TextView>(R.id.ageRestrictionId)?.text =
            movieDto.ageRestriction.toString()
        view?.findViewById<ImageView>(R.id.imageDetId)?.load(movieDto.imageUrl)

    }
}