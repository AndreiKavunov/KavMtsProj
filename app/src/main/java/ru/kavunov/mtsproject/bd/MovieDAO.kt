package ru.kavunov.mtsproject.bd

import androidx.room.*




@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel)

    @Update
    fun update(movieModel: MovieModel)

    @Delete
    fun delete(movieModel: MovieModel)

    @Query("SELECT * FROM MovieModel WHERE movId == :id")
    fun getByName(id: Long): MovieModel

    @Query("SELECT * FROM MovieModel")
    fun get(): List<MovieModel>

    @Transaction
    @Query("SELECT * FROM MovieModel")
    fun getMovlistsWithAct(): List<MovListWithAct>
}