package ru.kavunov.mtsproject.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.goToach
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.ItemMovieBinding


class MovieAdapter(ListMain: List<MovieDto>):
    RecyclerView.Adapter<MovieHolder>()
 {
    var movietList = ListMain.toMutableList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
      holder.bind(movietList[position])
    }

    override fun getItemCount(): Int {
        return movietList.size
    }
    fun changeList(list: ArrayList<MovieDto>){
        movietList.clear()
        Log.d("tag", list.toString())
        movietList.addAll(list)
        notifyDataSetChanged()

    }

}

class MovieHolder(item:View):RecyclerView.ViewHolder(item) {
    val building = ItemMovieBinding.bind(item)
    fun bind(movie: MovieDto){building.apply {
        filmImg.load(movie.imageUrl)
        filmName.text = movie.title
        filmContent.text = movie.description
        filmOgr.text = movie.ageRestriction.toString() + "+"
        filmRating.rating = movie.rateScore.toFloat()
        itemView.setOnClickListener { view ->
        (filmImg.context as goToach)?.clickToach(movie)}


    }

    }
}