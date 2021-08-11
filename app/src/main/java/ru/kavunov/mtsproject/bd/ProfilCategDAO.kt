package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface ProfilCategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilCategTableModel: ProfilCategTableModel)

    @Update
    fun update(profilCategTableModel: ProfilCategTableModel)

    @Delete
    fun delete(profilCategTableModel: ProfilCategTableModel)

    @Query("SELECT * FROM ProfilCategTableModel WHERE categId == :id")
    fun getByName(id: Long): ProfilCategTableModel

    @Query("SELECT * FROM ProfilCategTableModel")
    fun get(): List<ProfilCategTableModel>

//    @Query("SELECT ProfilModel.name, CategoryModel.category FROM ProfilModel, CategoryModel " +
//                "WHERE films.id == actors.film_id")
//    fun getFilmsWithActors(): List<FilmAndActor>
}