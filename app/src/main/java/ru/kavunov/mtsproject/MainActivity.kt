package ru.kavunov.mtsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kavunov.mtsproject.DTC.Categorie
import ru.kavunov.mtsproject.DTC.MovieDto
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


class MainActivity : AppCompatActivity(), MovieClickListener {

    var listMov= ArrayList<MovieDto>()
    val listCateg : List<Categorie> = CategoryDataSourceImpl().getMovies()

    private val adapterCateg = CategoryAdapter(listCateg)

    lateinit var adapterMovie: MovieAdapter



    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listMov.addAll(MoviesDataSourceImpl().getMovies())
        init()


    }

    fun init(){
        adapterMovie = MovieAdapter(listMov)
        binding.RcCateg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.RcCateg.adapter = adapterCateg
        binding.RcMovie.layoutManager = GridLayoutManager(this, 2)
        binding.RcMovie.adapter = adapterMovie
        }



    override fun clickToach(a: MovieDto) {
        Toast.makeText(this, a.title, Toast.LENGTH_SHORT).show()


    }
//    fun updateList(){
//        while (listMov.size < 6){
//            var film = MoviesDataSourceImpl().getMovies().random()
//            if(film !in listMov)
//            listMov.add(film)
//        }
//    }
//    fun onClickkk(view: View){
//        listMov.clear()
//        updateList()
//        adapterMovie.changeList(listMov)
//        Log.d("tag", "www")
//    }

}