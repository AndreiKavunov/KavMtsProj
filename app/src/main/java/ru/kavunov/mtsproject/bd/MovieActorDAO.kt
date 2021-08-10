package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface MovieActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieAct: MovieAct)

    @Update
    fun update(movieAct: MovieAct)

    @Delete
    fun delete(movieAct: MovieAct)

    @Query("SELECT * FROM MovieAct WHERE movId == :id")
    fun getByName(id: Long): MovieAct

    @Query("SELECT * FROM MovieAct")
    fun get(): List<MovieAct>


}