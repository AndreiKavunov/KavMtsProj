package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface ProfilCategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilCateg: ProfilCateg)

    @Update
    fun update(profilCateg: ProfilCateg)

    @Delete
    fun delete(profilCateg: ProfilCateg)

    @Query("SELECT * FROM ProfilCateg WHERE categId == :id")
    fun getByName(id: Long): ProfilCateg

    @Query("SELECT * FROM ProfilCateg")
    fun get(): List<ProfilCateg>

//    @Query("SELECT ProfilModel.name, CategoryModel.category FROM ProfilModel, CategoryModel " +
//                "WHERE films.id == actors.film_id")
//    fun getFilmsWithActors(): List<FilmAndActor>
}