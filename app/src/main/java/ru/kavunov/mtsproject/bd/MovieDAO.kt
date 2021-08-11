package ru.kavunov.mtsproject.bd

import androidx.room.*




@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieTableModel: MovieTableModel)

    @Update
    fun update(movieTableModel: MovieTableModel)

    @Delete
    fun delete(movieTableModel: MovieTableModel)

    @Query("SELECT * FROM MovieModel WHERE movId == :id")
    fun getByName(id: Long): MovieTableModel

    @Query("SELECT * FROM MovieModel")
    fun get(): List<MovieTableModel>

    @Transaction
    @Query("SELECT * FROM MovieModel WHERE movId == :id")
    fun getMovlistsWithAct(id: Long): List<MovListWithAct>
}