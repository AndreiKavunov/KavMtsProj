package ru.kavunov.mtsproject.bd

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProfilDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(profilModel: ProfilModel)

    @Update
    fun update(profilModel: ProfilModel)

    @Delete
    fun delete(profilModel: ProfilModel)

    @Query("SELECT * FROM ProfilModel WHERE name == :name")
    fun getByName(name: String): ProfilModel

    @Query("SELECT * FROM ProfilModel")
    fun get(): List<ProfilModel>

    @Transaction
    @Query("SELECT * FROM ProfilModel")
    fun getPlaylistsWithSongs(): List<ProfListWithCateg>

//    @Transaction
//    @Query("SELECT * FROM ProfilModel")
//    fun getProfilWithCateg(): List<PlaylistWithSongs>

}
