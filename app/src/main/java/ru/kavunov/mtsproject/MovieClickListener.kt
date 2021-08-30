package ru.kavunov.mtsproject

import android.content.Context
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import ru.kavunov.mtsproject.bd.MovieTable

interface MovieClickListener {
    fun clickDetail(position: Long, movieTable: MovieTable, titleView: TextView,
                    image: ImageView,contentView: TextView, rating: RatingBar, age: TextView)


}