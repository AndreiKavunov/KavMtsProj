package ru.kavunov.mtsproject

import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import ru.kavunov.mtsproject.adapter.CategoryAdapter
import ru.kavunov.mtsproject.adapter.MovieAdapter
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding


class MainActivity : AppCompatActivity() {
    private val adapterCateg = CategoryAdapter()
    private val adapterMovie = MovieAdapter()
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init1()
        init2()

    }

    fun init1(){
        binding.RcCateg.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.RcCateg.adapter = adapterCateg
        for(i in adapterCateg.list1){
            adapterCateg.categtList.add(i)
        }

    }
    fun init2(){
        binding.RcMovie.layoutManager = GridLayoutManager(this, 2)
        binding.RcMovie.adapter = adapterMovie
        for(i in adapterMovie.list2){
            adapterMovie.movietList.add(i)
        }

    }
}