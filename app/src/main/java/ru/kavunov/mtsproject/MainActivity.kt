package ru.kavunov.mtsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kavunov.mtsproject.DTC.Categories
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


class MainActivity : AppCompatActivity(), OnClickTest {

    val listMov : List<MovieDto> = MoviesDataSourceImpl().getMovies()
    lateinit var listMov1 = emptyArray<MovieDto()

    val listCateg : List<Categories> = CategoryDataSourceImpl().getMovies()

    private val adapterCateg = CategoryAdapter(listCateg)
//   private val adapterMovie = MovieAdapter(listMov, this, onMovieClick = {Toast.makeText(this, "Фильм", Toast.LENGTH_SHORT).show()})
    private val adapterMovie = MovieAdapter(listMov, this)



    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()


    }

    fun init(){
        binding.RcCateg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.RcCateg.adapter = adapterCateg
        binding.RcMovie.layoutManager = GridLayoutManager(this, 2)
        binding.RcMovie.adapter = adapterMovie
        }



    override fun clickTest(a: MovieDto) {
        Toast.makeText(this, a.title, Toast.LENGTH_SHORT).show()
        Log.d("tag", a.title)
    }

}