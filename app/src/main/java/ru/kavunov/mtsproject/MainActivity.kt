package ru.kavunov.mtsproject



import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kavunov.mtsproject.bd.MovieTable
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding



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

        runWorker(this)


    }

    override fun clickDetail(position: Long,image:ImageView) {

        val extrasConst = FragmentNavigatorExtras(

            image to "image"+position.toString(),

        )
        bundle.putString("MyArg", position.toString())
        Navigation
            .findNavController(this, R.id.nav_host_fragment)
            .navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle, null,  extrasConst)


    }


}




object Orient {var orInt = 1}









