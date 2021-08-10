package ru.kavunov.mtsproject.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProfilModel::class, CategoryModel::class, ProfilCateg::class], version = 8)
//@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profilDAO(): ProfilDAO
    abstract fun categDAO(): CategDAO
    abstract fun profilCategDAO(): ProfilCategDAO
//    abstract fun profListWithCategDAO(): ProfListWithCategDAO


//    companion object {
//        private const val DATABASE_NAME = "Films.db"
//        val instance: AppDatabase by lazy {
//            Room.databaseBuilder(
//                context.getApplicationContext(),
//                AppDatabase::class.java,
//                DATABASE_NAME
//            ).allowMainThreadQueries()
//                .fallbackToDestructiveMigration()
//                .build()

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

                return INSTANCE!!

            }
        }

    }


}