package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface MovieActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieActTableModel: MovieActTableModel)

    @Update
    fun update(movieActTableModel: MovieActTableModel)

    @Delete
    fun delete(movieActTableModel: MovieActTableModel)

    @Query("SELECT * FROM MovieActTableModel WHERE movId == :id")
    fun getByName(id: Long): MovieActTableModel

    @Query("SELECT * FROM MovieActTableModel")
    fun get(): List<MovieActTableModel>


}