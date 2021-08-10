package ru.kavunov.mtsproject.bd

import androidx.room.*



@Dao
interface ActorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actorModel: ActorModel)

    @Update
    fun update(actorModel: ActorModel)

    @Delete
    fun delete(actorModel: ActorModel)

    @Query("SELECT * FROM ActorModel WHERE actId == :id")
    fun getByName(id: Long): ActorModel

    @Query("SELECT * FROM ActorModel")
    fun get(): List<ActorModel>


}