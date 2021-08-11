package ru.kavunov.mtsproject.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kavunov.mtsproject.mvvm.model.*
import ru.mts.teta.summer.android.homework.list.data.features.movies.CategoryDataSourceImpl
import ru.mts.teta.summer.android.homework.list.data.features.movies.MoviesDataSourceImpl


@Database(entities = [ProfilTableModel::class, CategoryTableModel::class, ProfilCategTableModel::class, MovieTableModel::class,
                     ActorTableModel::class, MovieActTableModel::class], version = 32)
//@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilDAO(): ProfilDAO
    abstract fun categDAO(): CategDAO
    abstract fun profilCategDAO(): ProfilCategDAO
    abstract fun movieDAO(): MovieDAO
    abstract fun actorDAO():ActorDAO
    abstract fun movieActorDAO(): MovieActorDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "Films.db"
        fun getDataseClient(context: Context) : AppDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()



//                    CoroutineScope(Dispatchers.Main).launch() {
//                    if(ProfilModel.getAll(context)?.size == 0){
//                    ProfilCatModel.insertData(context, 1, 2)
//                    ProfilCatModel.insertData(context, 1, 4)
//                    ProfilCatModel.insertData(context, 1, 5)
//                    ProfilModel.insertData(
//                        context,
//                        id = 1,
//                        name = "Иван",
//                        email = "Ivan@mail.ru",
//                        phone = "8-909-000-9999",
//                        foto = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/oTB9vGIBacH5aQNS0pUM74QSWuf.jpg",
//                    )
//                    for (x in CategoryDataSourceImpl().getMovies()) CategModel.insertData(
//                        context,
//                        0,
//                        x.category
//                    )
//                    var idF = 0L
//                    var idA = 0L
//                    for (x in MoviesDataSourceImpl().getMovies()[0]) {
//                        idF ++
//                        MovieModel.insertData(
//                        context, id = idF, title = x.title, description = x.description,
//                        rateScore = x.rateScore, ageRestriction = x.ageRestriction.toString(), imageUrl = x.imageUrl
//                    )
//
//                        for(i in x.actor){
//                            idA++
//                            ActorModel.insertData(context, id = idA, imgAct = i.img, nameAct = i.name)
//
//                            MovieActModel.insertData(context, idF, idA)}
//                    }
//                }}
                return INSTANCE!!

            }
        }

    }



}