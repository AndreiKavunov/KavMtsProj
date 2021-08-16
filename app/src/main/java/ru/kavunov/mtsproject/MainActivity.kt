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
import okhttp3.*
import okio.IOException

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
        okHTTPonly()

    }

    override fun clickDetail(position: Long) {
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle)
          }


}

fun okHTTPonly(){
//    var x: String = "ss"
    val client = OkHttpClient()
    val request = Request.Builder()
//            .url("https://www.simplifiedcoding.net/demos/marvel/")
            .url("https://api.themoviedb.org/3/movie/popular/550?api_key=b62341778732f78e2661370039f79b84")

//            .url("https://api.themoviedb.org/3/movie/550?api_key=b62341778732f78e2661370039f79b84")

        .build()

//            client.newCall(request).execute().use { response ->
//                if (!response.isSuccessful) throw IOException("Unexpected code $response")
//                val result = response.body!!.string()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response){
            response.use {
                try {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val result = response.body?.string() ?: "error"
//                        x = result
                    Log.d("tag11", result.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    })

}



object Orient {var orInt = 1}