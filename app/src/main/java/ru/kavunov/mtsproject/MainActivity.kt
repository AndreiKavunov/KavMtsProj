package ru.kavunov.mtsproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding


class MainActivity : AppCompatActivity(), MovieClickListener {


    private var listfilmFragment: ListFilmFragment? = null
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrag, ProfilFragment())
            .addToBackStack(null)
            .commit()
    }

    fun launchHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrag, ListFilmFragment())
          //  .addToBackStack(null)
            .commit()
    }
companion object {
    const val LIST_FILM_FRAGMENT_TAG = "ListFilmFragment"
}

    override fun clickDetail(position: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrag, DetailkFragment.newInstance(position))
            .addToBackStack(null)
            .commit()
    }


}