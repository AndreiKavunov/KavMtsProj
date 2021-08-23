package ru.kavunov.mtsproject.bd

import androidx.room.*



@Dao
interface ActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actorTable: ActorTable)

    @Update
    fun update(actorTable: ActorTable)

//    @Delete
//    fun delete(actorTable: ActorTable)

    @Query("DELETE FROM ActorModel")
    fun delete()

    @Query("SELECT * FROM ActorModel WHERE actId == :id")
    fun getByName(id: Long): ActorTable

    @Query("SELECT * FROM ActorModel")
    fun get(): List<ActorTable>


}