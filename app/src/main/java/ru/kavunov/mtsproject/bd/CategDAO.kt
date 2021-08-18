package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface CategDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryTable: CategoryTable)

    @Update
    fun update(categoryTable: CategoryTable)

    @Delete
    fun delete(categoryTable: CategoryTable)

    @Query("SELECT * FROM CategoryModel WHERE categId == :id")
    fun getByName(id: Long): CategoryTable

    @Query("SELECT * FROM CategoryModel")
    fun get(): List<CategoryTable>


}