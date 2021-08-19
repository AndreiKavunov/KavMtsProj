package ru.kavunov.mtsproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import okhttp3.*
import okio.IOException

import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding
import ru.kavunov.mtsproject.DTC.*
import ru.kavunov.mtsproject.recponse.App
import ru.kavunov.mtsproject.recponse.CategResp
import ru.kavunov.mtsproject.recponse.respModel.AgeRecpModel
import ru.kavunov.mtsproject.recponse.respModel.CategRecpModel

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
        ListFilm.idFilm = position.toString()
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle)
          }


}




object Orient {var orInt = 1}






fun okHTTPonly(){

    val client = OkHttpClient()
    val request = Request.Builder()
//        .url("https://api.themoviedb.org/3/movie/436969?api_key=b62341778732f78e2661370039f79b84&language=RU")
//        .url("https://api.themoviedb.org/3/movie/451048/release_dates?api_key=b62341778732f78e2661370039f79b84&language=RU")
        .url("https://api.themoviedb.org/3/movie/436969/credits?api_key=b62341778732f78e2661370039f79b84&language=RU")
//        .url("https://api.themoviedb.org/3/genre/movie/list?api_key=b62341778732f78e2661370039f79b84&language=RU")
        .build()
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
//            Log.d("tag11", "33333")
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response){
            response.use {
                try {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val result = response.body?.string() ?: "error"


//                    val json = Json { ignoreUnknownKeys = true }
//                    val objectResponse = json.decodeFromString<FilmResponse>(result)
                    Log.d("tag11", "11122" + result.toString())
//                    Log.d("tag11", "11122" + x.toString())

//                    Log.d("tag11", "222" + objectResponse.toString())
                } catch (e: IOException) {

                    e.printStackTrace()
                }
            }

        }
    })

}




