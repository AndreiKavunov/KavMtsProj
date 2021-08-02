package ru.kavunov.mtsproject

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import ru.kavunov.mtsproject.databinding.ActivityMovieDetailsBinding

class MainActivity : AppCompatActivity(), MovieClickListener {

    var flagFragment: Int = 1
    val bundle = Bundle()


    private var listfilmFragment: ListFilmFragment? = null
    lateinit var binding: ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        Orient.orInt = getResources().getConfiguration().orientation
        binding.BoNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {launchHome1()}
                R.id.profil -> {launchProfil1()}
            }
            true
        }



//        if(savedInstanceState == null){
//            listfilmFragment = ListFilmFragment()
////            listfilmFragment?.apply {
////                supportFragmentManager.beginTransaction()
////                    .add(R.id.mainFrag, this, LIST_FILM_FRAGMENT_TAG)
////                    .commit()
////            }
//            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.listFilmFragment)
//        }
//        else {
//            listfilmFragment =
//                supportFragmentManager.findFragmentByTag(LIST_FILM_FRAGMENT_TAG) as? ListFilmFragment
//        }

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

    fun launchProfil1(){

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.profilFragment)
    }
    fun launchHome1(){
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.popBackStack(R.id.listFilmFragment,true)
        navController.navigate(R.id.listFilmFragment)



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
//        supportFragmentManager.beginTransaction()
//            .addToBackStack("3")
//            .replace(R.id.mainFrag, DetailkFragment.newInstance(position), "detail")
//            .commit()
        bundle.putString("MyArg", position.toString())
        Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.detailkFragment, bundle)
        flagFragment = 2

    }


//override fun onBackPressed() {
//    super.onBackPressed()
//    if(flagFragment == 2)launchHome()
//}

}

object Orient {var orInt = 1}