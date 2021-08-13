package ru.kavunov.mtsproject

import android.app.Activity
import android.app.ProgressDialog

import android.content.Context

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding
import ru.kavunov.mtsproject.mvvm.model.*
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl

class MainActivity : AppCompatActivity(), MovieClickListener {

    val bundle = Bundle()
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Orient.orInt = getResources().getConfiguration().orientation


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.BoNav)
            .setupWithNavController(navController)


    }

    override fun clickDetail(position: Long) {
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle)
          }


}




object Orient {var orInt = 1}