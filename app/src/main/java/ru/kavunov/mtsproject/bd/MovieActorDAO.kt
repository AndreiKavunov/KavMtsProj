package ru.kavunov.mtsproject.bd

import androidx.room.*


@Dao
interface MovieActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieActTable: MovieActTable)

    @Update
    fun update(movieActTable: MovieActTable)
//
//    @Delete
//    fun delete(movieActTable: MovieActTable)

    @Query("SELECT * FROM MovieActTable WHERE movId == :id")
    fun getByName(id: Long): MovieActTable

    @Query("SELECT * FROM MovieActTable")
    fun get(): List<MovieActTable>

    @Query("DELETE FROM MovieActTable")
    fun delete()


}