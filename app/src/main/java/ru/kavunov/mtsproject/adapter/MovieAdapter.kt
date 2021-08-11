package ru.kavunov.mtsproject.adapter


import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

import ru.kavunov.mtsproject.DTC.Categorie

import ru.kavunov.mtsproject.MovieClickListener
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.bd.MovieTableModel
import ru.kavunov.mtsproject.databinding.ItemMovieBinding



class MovieAdapter(): RecyclerView.Adapter<MovieHolder>() {
//    var movietList = ListMain.toMutableList()
    var movietList: MutableList<MovieTableModel> = ArrayList()



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


    fun changeList(movie: List<MovieTableModel>?) {
        if (movie!=null){
            movietList.clear()
            movietList.addAll(movie)
            notifyDataSetChanged()

        }

    }

}
class MovieHolder(item:View):RecyclerView.ViewHolder(item) {

    val building = ItemMovieBinding.bind(item)
    fun bind(movie: MovieTableModel){building.apply {
        filmImg.load(movie.imageUrl)
        filmName.text = movie.title
        filmContent.text = movie.description
        filmOgr.text = movie.ageRestriction.toString() + "+"
        filmRating.rating = movie.rateScore.toFloat()
        filmImg.setOnClickListener { view ->
            (itemView.context as MovieClickListener)?.clickDetail(movie.movId)}

    }

    }
}