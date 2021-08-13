package ru.kavunov.mtsproject.bd

import androidx.room.*




@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieTable: MovieTable)

    @Update
    fun update(movieTable: MovieTable)

    @Delete
    fun delete(movieTable: MovieTable)

    @Query("SELECT * FROM MovieModel WHERE movId == :id")
    fun getByName(id: Long): MovieTable

    @Query("SELECT * FROM MovieModel")
    fun get(): List<MovieTable>

    @Transaction
    @Query("SELECT * FROM MovieModel WHERE movId == :id")
    fun getMovlistsWithAct(id: Long): List<MovListWithAct>
}