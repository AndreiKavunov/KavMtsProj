package ru.kavunov.mtsproject.bd

import androidx.room.*



@Dao
interface ActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actorTableModel: ActorTableModel)

    @Update
    fun update(actorTableModel: ActorTableModel)

    @Delete
    fun delete(actorTableModel: ActorTableModel)

    @Query("SELECT * FROM ActorModel WHERE actId == :id")
    fun getByName(id: Long): ActorTableModel

    @Query("SELECT * FROM ActorModel")
    fun get(): List<ActorTableModel>


}