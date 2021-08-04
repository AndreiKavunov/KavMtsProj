package ru.kavunov.mtsproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding

class MainActivity : AppCompatActivity(), MovieClickListener {
    var flagFragment: Int = 1


    private var listfilmFragment: ListFilmFragment? = null
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Orient.orInt = getResources().getConfiguration().orientation
        binding.BoNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {launchHome()}
                R.id.profil -> {launchProfil()}
            }
            true
        }


        if(savedInstanceState == null){
            listfilmFragment = ListFilmFragment()
            listfilmFragment?.apply {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainFrag, this, LIST_FILM_FRAGMENT_TAG)
                    .commit()
            } }
        else {
            listfilmFragment =
                supportFragmentManager.findFragmentByTag(LIST_FILM_FRAGMENT_TAG) as? ListFilmFragment
        }
    }


    fun launchProfil() {
        if (flagFragment == 1){
        supportFragmentManager.beginTransaction()
            .addToBackStack("2")
            .replace(R.id.mainFrag, ProfilFragment(), "profil")
            .commit()
        flagFragment = 2
    }
        else{
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrag, ProfilFragment(), "profil")
                .commit()
            flagFragment = 2
        }
    }

    fun launchHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrag, ListFilmFragment(), "main")
            .commit()
        flagFragment = 1

    }
companion object {
    const val LIST_FILM_FRAGMENT_TAG = "ListFilmFragment"
}

    override fun clickDetail(position: Int) {
        supportFragmentManager.beginTransaction()
            .addToBackStack("3")
            .replace(R.id.mainFrag, DetailkFragment.newInstance(position), "detail")
            .commit()
        flagFragment = 2
    }


override fun onBackPressed() {
    super.onBackPressed()
    if(flagFragment == 2)launchHome()
}

}

object Orient {var orInt = 1}