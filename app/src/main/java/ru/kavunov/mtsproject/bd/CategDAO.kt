package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface CategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryTableModel: CategoryTableModel)

    @Update
    fun update(categoryTableModel: CategoryTableModel)

    @Delete
    fun delete(categoryTableModel: CategoryTableModel)

    @Query("SELECT * FROM CategoryModel WHERE categId == :id")
    fun getByName(id: Long): CategoryTableModel

    @Query("SELECT * FROM CategoryModel")
    fun get(): List<CategoryTableModel>


}