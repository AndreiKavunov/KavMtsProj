package ru.kavunov.mtsproject.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.kavunov.mtsproject.ChangeDetails2
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.R
import ru.kavunov.mtsproject.databinding.ItemMovieBinding


class MovieAdapter(ListMain: List<MovieDto>, context: Context): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    var movietList = ListMain.toMutableList()
    val onClickTest: ChangeDetails2 = context as ChangeDetails2
    class MovieHolder(item:View):RecyclerView.ViewHolder(item) {

        val building = ItemMovieBinding.bind(item)
        fun bind(movie: MovieDto){building.apply {
            filmImg.load(movie.imageUrl)
            filmName.text = movie.title
            filmContent.text = movie.description
            filmOgr.text = movie.ageRestriction.toString() + "+"
            filmRating.rating = movie.rateScore.toFloat()

        }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
      holder.bind(movietList[position])
        holder.itemView.setOnClickListener()
    }

    override fun getItemCount(): Int {
        return movietList.size
    }
    fun changeList(){
        movietList.clear()
        movietList.addAll(movietList)
        notifyDataSetChanged()

    }

}