package ru.kavunov.mtsproject.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProfilTable::class, CategoryTable::class, ProfilCategTable::class, MovieTable::class,
                     ActorTable::class, MovieActTable::class], version = 46)
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

                return INSTANCE!!

            }
        }
    }



}