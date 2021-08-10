package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface CategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryModel: CategoryModel)

    @Update
    fun update(categoryModel: CategoryModel)

    @Delete
    fun delete(categoryModel: CategoryModel)

    @Query("SELECT * FROM CategoryModel WHERE categId == :id")
    fun getByName(id: Long): CategoryModel

    @Query("SELECT * FROM CategoryModel")
    fun get(): List<CategoryModel>

//    @Transaction
//    @Query("SELECT * FROM ProfilModel")
//    fun getProfilWithCateg(): List<PlaylistWithSongs>

}