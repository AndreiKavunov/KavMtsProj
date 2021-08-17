package ru.kavunov.mtsproject

import android.app.Activity
import android.app.Application
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
import com.google.gson.internal.bind.TypeAdapters.URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializer

import okhttp3.*
import okio.IOException
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding
import ru.kavunov.mtsproject.mvvm.model.*
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlinx.serialization.Serializable
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
    val client = OkHttpClient()
    val request = Request.Builder()
//            .url("https://api.themoviedb.org/3/movie/550?api_key=b62341778732f78e2661370039f79b84")
            .url("https://api.themoviedb.org/3/discover/movie?&sort_by=popularity.desc&api_key=b62341778732f78e2661370039f79b84")


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
                    val json = Json { ignoreUnknownKeys = true }
                    val objectResponse = json.decodeFromString<ObjectResponse>(result)
//                        x = result
                    Log.d("tag11", result.toString())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        }
    })

}

@Serializable
data class ObjectResponse(
    @SerialName("results") val results: List<FilmResponse>
)
@Serializable
data class FilmResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("overview") val overview: String,
//    val source: SourceResponse
)




object Orient {var orInt = 1}