package ru.kavunov.mtsproject.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load

import ru.kavunov.mtsproject.MovieClickListener
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.databinding.ItemMovieBinding



class MovieAdapter(): RecyclerView.Adapter<MovieHolder>() {
//    var movietList = ListMain.toMutableList()
    var movietList: MutableList<MovieTable> = ArrayList()



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


    fun changeList(movie: List<MovieTable>?) {
        if (movie!=null){
            movietList.clear()
            movietList.addAll(movie)
            notifyItemInserted(movie.size)
            notifyDataSetChanged()


        }

    }

}
class MovieHolder(item:View):RecyclerView.ViewHolder(item) {

    val building = ItemMovieBinding.bind(item)
    fun bind(movie: MovieTable){building.apply {
        filmImg.transitionName = "image${movie.movId}"
        filmContent.transitionName = "content${movie.movId}"
        filmRating.transitionName = "rating${movie.movId}"
        filmOgr.transitionName = "age${movie.movId}"

        filmImg.load(movie.imageUrl)
        filmName.text = movie.title
        filmName.transitionName = movie.movId.toString()
        filmContent.text = movie.description
        filmOgr.text = movie.ageRestriction
        filmRating.rating = movie.rateScore
        itemView.setOnClickListener { view ->
            (itemView.context as MovieClickListener)?.clickDetail(movie.movId, movie, filmName,filmImg, filmContent, filmRating, filmOgr)}

    }

    }
}