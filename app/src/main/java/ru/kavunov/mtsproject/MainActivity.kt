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
        test111(this)

    }

    override fun clickDetail(position: Long) {
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle)
          }


}
fun test111(context: Context){
    CoroutineScope(Dispatchers.Main).launch() {
        if(ProfilModel.getAll(context)?.size == 0){
            ProfilCatModel.insertData(context, 1, 2)
            ProfilCatModel.insertData(context, 1, 4)
            ProfilCatModel.insertData(context, 1, 5)
            ProfilModel.insertData(
                context,
                id = 1,
                name = "Иван",
                email = "Ivan@mail.ru",
                phone = "8-909-000-9999",
                foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
            )
            for (x in CategoryDataSourceImpl().getMovies()) CategModel.insertData(
                context,
                0,
                x.category
            )
            var idF = 0L
            var idA = 0L
            for (x in MoviesDataSourceImpl().getMovies()[0]) {
                idF ++
                MovieModel.insertData(
                    context, id = idF, title = x.title, description = x.description,
                    rateScore = x.rateScore, ageRestriction = x.ageRestriction.toString(), imageUrl = x.imageUrl
                )

                for(i in x.actor){
                    idA++
                    ActorModel.insertData(context, id = idA, imgAct = i.img, nameAct = i.name)

                    MovieActModel.insertData(context, idF, idA)}
            }
        }}}


object Orient {var orInt = 1}