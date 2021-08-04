package ru.kavunov.mtsproject

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding

class MainActivity : AppCompatActivity(), MovieClickListener {

    val bundle = Bundle()
    private var listfilmFragment: ListFilmFragment? = null
    lateinit var binding: ActivityMovieDetailsBinding
    private val progressDialog by lazy { ProgressDialog.show(this, "", getString(R.string.actor1)) }
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

    override fun clickDetail(position: Int) {
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.action_listFilmFragment_to_detailkFragment, bundle)
          }
    data class ViewState(
        val isDownloaded: Boolean = false
    )
    private fun render(viewState: ViewState) = with(viewState) {
        if (isDownloaded) {
            progressDialog.show()
        } else {
            progressDialog.dismiss()
        }
    }
}

object Orient {var orInt = 1}